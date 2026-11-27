package edu.sandiego.comp305;

import java.time.Instant;

public class Alert {

    private final String ruleName;

    public Alert(
            final String ruleName,
            final Severity severity,
            final String entity,
            final Instant time) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Severity getSeverity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getEntity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Instant getTime() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
