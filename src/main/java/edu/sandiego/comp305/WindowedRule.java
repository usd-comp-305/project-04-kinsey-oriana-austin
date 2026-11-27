package edu.sandiego.comp305;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.HashMap;

public abstract class WindowedRule implements DetectionRule {
    private final int windowSeconds;

    private final int threshold;

    private final Map<String, Deque<Instant>> eventTimes = new HashMap<>();

    protected WindowedRule(final int windowSeconds, final int threshold) {
        this.windowSeconds = validatePositive(windowSeconds, "windowSeconds");
        this.threshold = validatePositive(threshold, "threshold");
    }

    @Override
    @SuppressWarnings({"deprecation", "checkstyle:NoFinalizer"})
    protected final void finalize() {

    }

    private static int validatePositive(final int value, final String name) {
        if (value <= 0) {
            throw new IllegalArgumentException(name + " must be positive");
        }
        return value;
    }

    @Override
    public List<Alert> evaluate(
            final LogEntry entry,
            final ProfileStore profiles) {
        Objects.requireNonNull(entry, "entry is required");
        Objects.requireNonNull(profiles, "profiles is required");

        if (!isTriggered(entry, profiles)) {
            return List.of();
        }
        final Instant now = entry.getTimestamp();
        final Instant cutoff = now.minusSeconds(windowSeconds);
        final Deque<Instant> times = eventTimes.computeIfAbsent(
                entry.getSourceIp(), key -> new ArrayDeque<>());

        while (!times.isEmpty() && times.peekFirst().isBefore(cutoff)) {
            times.removeFirst();
        }
        times.addLast(now);

        if (times.size() >= threshold) {
            return List.of(buildAlert(entry));
        }
        return List.of();
    }

    protected int getWindowSeconds() {
        return windowSeconds;
    }

    protected int getThreshold() {
        return threshold;
    }

    protected abstract boolean isTriggered(
            final LogEntry entry,
            final ProfileStore profiles);

    protected abstract Alert buildAlert(final LogEntry entry);
}
