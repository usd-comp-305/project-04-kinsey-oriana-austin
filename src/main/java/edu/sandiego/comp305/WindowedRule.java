package edu.sandiego.comp305;

import java.util.List;

public abstract class WindowedRule implements DetectionRule {
    private final int windowSeconds;
    private final int threshold;

    protected WindowedRule(final int windowSeconds, final int threshold) {
        this.windowSeconds =windowSeconds;
        this.threshold =threshold;
    }

    @Override
    public List<Alert> evaluate(
            final LogEntry entry,
            final ProfileStore profiles) {
        return List.of();
    }

    protected abstract boolean isTriggered(
            final LogEntry entry,
            final ProfileStore profiles);

    protected abstract Alert buildAlert(final LogEntry entry);
}
