package edu.sandiego.comp305;

import java.time.Instant;

public class LogEntry {
    private final Instant timestamp;
    private final String sourceIp;
    private final String username;

    public LogEntry(
            final Instant timestamp,
            final String sourceIp,
            final String username,
            final EventType eventType,
            final int port) {
        this.timestamp = timestamp;
        this.sourceIp = sourceIp;
        this.username = username;
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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getPort() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
