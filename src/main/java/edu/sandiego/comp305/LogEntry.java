package edu.sandiego.comp305;

import java.time.Instant;

public class LogEntry {
    private final Instant timestamp;

    public LogEntry(
            final Instant timestamp,
            final String sourceIp,
            final String username,
            final EventType eventType,
            final int port) {
        this.timestamp = timestamp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getSourceIp() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getUsername() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public EventType getEventType() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getPort() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
