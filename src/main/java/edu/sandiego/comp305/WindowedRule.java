package edu.sandiego.comp305;

import java.time.Instant;
import java.util.*;

public abstract class WindowedRule implements DetectionRule {
    private final int windowSeconds;
    private final int threshold;
    private final Map<String, Deque<Instant>> eventTimes = new HashMap<>();

    protected WindowedRule(final int windowSeconds, final int threshold) {
        this.windowSeconds =windowSeconds;
        this.threshold =threshold;
    }

    @Override
    public List<Alert> evaluate(
            final LogEntry entry,
            final ProfileStore profiles) {
        if (!isTriggered(entry, profiles)) {
            return List.of();
        }
        final Deque<Instant> times = eventTimes.computeIfAbsent(
                entry.getSourceIp(), key -> new ArrayDeque<>());
        times.addLast(entry.getTimestamp());

        if (times.size() >= threshold) {
            return List.of(buildAlert(entry));
        }
        return List.of();
    }

    protected abstract boolean isTriggered(
            final LogEntry entry,
            final ProfileStore profiles);

    protected abstract Alert buildAlert(final LogEntry entry);
}
