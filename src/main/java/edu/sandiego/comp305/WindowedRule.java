package edu.sandiego.comp305;

import java.util.List;

public abstract class WindowedRule implements DetectionRule {

    @Override
    public List<Alert> evaluate(
            final LogEntry entry,
            final ProfileStore profiles) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    protected abstract boolean isTriggered(
            final LogEntry entry,
            final ProfileStore profiles);

    protected abstract Alert buildAlert(final LogEntry entry);
}
