package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Objects;

public class LogEntry {
    private static final int MAX_PORT = 65535;

    private final Instant timestamp;

    private final String sourceIp;

    private final String username;

    private final EventType eventType;

    private final int port;

    public LogEntry(
            final Instant timestamp,
            final String sourceIp,
            final String username,
            final EventType eventType,
            final int port) {
        this.timestamp = Objects.requireNonNull(
                timestamp, "timestamp is required");
        this.sourceIp = Objects.requireNonNull(
                sourceIp, "source IP is required");
        this.username = Objects.requireNonNull(
                username, "username is required");
        this.eventType = Objects.requireNonNull(
                eventType, "event type required");
        this.port = validatePort(port);
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public String getUsername() {
        return username;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getPort() {
        return port;
    }

    private static int validatePort(final int port) {
        if (port < 0 || port > MAX_PORT) {
            throw new IllegalArgumentException(
                    "port must be between 0 and " + MAX_PORT);
        }
        return port;
    }
}
