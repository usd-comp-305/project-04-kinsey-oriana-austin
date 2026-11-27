package edu.sandiego.comp305;

public class PortScanRule extends WindowedRule {

    private static final int DEFAULT_WINDOW_SECONDS = 60;

    private static final int DEFAULT_THRESHOLD = 5;

    public PortScanRule() {
        this(DEFAULT_WINDOW_SECONDS, DEFAULT_THRESHOLD);
    }

    public PortScanRule(final int windowSeconds, final int threshold) {
        super(windowSeconds, threshold);
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected boolean isTriggered(
            final LogEntry entry,
            final ProfileStore profiles) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected Alert buildAlert(final LogEntry entry) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
