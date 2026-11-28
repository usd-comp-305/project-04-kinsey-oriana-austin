package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Objects;

public final class Alert {

    private final String ruleName;

    private final Severity severity;

    private final String entity;

    private final Instant time;

    public Alert(
            final String ruleName,
            final Severity severity,
            final String entity,
            final Instant time) {
        this.ruleName = validateString(ruleName, "rule name");
        this.severity = Objects.requireNonNull(
                severity, "severity is required");
        this.entity = validateString(entity, "entity");
        this.time = Objects.requireNonNull(time, "time is required");
    }

    private static String validateString(
            final String value, final String fieldName) {
        Objects.requireNonNull(value, fieldName + " is required");

        if (value.isEmpty()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be empty");
        }

        return value;
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
