package edu.sandiego.comp305;

import java.time.Instant;

public class Alert {

    private final String ruleName;
    private final Severity severity;

    public Alert(
            final String ruleName,
            final Severity severity,
            final String entity,
            final Instant time) {
        this.ruleName = ruleName;
        this.severity = severity;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getEntity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Instant getTime() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
