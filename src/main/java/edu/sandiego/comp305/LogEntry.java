package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Objects;

public class LogEntry {
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
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp is required");
        this.sourceIp = Objects.requireNonNull(sourceIp, "source IP is required");
        this.username = username;
        this.eventType = eventType;
        this.port = port;
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
}
