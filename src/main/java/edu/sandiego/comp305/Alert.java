package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Objects;

public class Alert {

    private final String ruleName;

    private final Severity severity;

    private final String entity;

    private final Instant time;

    public Alert(
            final String ruleName,
            final Severity severity,
            final String entity,
            final Instant time) {

        this.ruleName = Objects.requireNonNull(
                ruleName, "rule name is required");

        if (ruleName.isEmpty()) {
            throw new IllegalArgumentException("rule name cannot be empty");
        }

        this.severity = Objects.requireNonNull(
                severity, "severity is required");

        this.entity = Objects.requireNonNull(
                entity, "entity is required");

        this.time = Objects.requireNonNull(
                time, "time is required");
    }

    public String getRuleName() {
        return ruleName;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getEntity() {
        return entity;
    }

    public Instant getTime() {
        return time;
    }
}
