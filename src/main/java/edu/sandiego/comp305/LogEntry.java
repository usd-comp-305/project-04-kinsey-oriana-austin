package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public final class LogEntry {
    private static final int MAX_PORT = 65535;

    private final Instant timestamp;

    private final String sourceIp;

    private final String username;

    private final EventType eventType;

    private final int port;

    private final Set<String> tags;

    public LogEntry(
            final Instant timestamp,
            final String sourceIp,
            final String username,
            final EventType eventType,
            final int port) {
        this(timestamp, sourceIp, username, eventType, port, Set.of());
    }

    public LogEntry(
            final Instant timestamp,
            final String sourceIp,
            final String username,
            final EventType eventType,
            final int port,
            final Set<String> tags) {
        this.timestamp = Objects.requireNonNull(
                timestamp, "timestamp is required");
        this.sourceIp = Objects.requireNonNull(
                sourceIp, "source IP is required");
        this.username = Objects.requireNonNull(
                username, "username is required");
        this.eventType = Objects.requireNonNull(
                eventType, "event type required");
        this.port = validatePort(port);
        this.tags = Set.copyOf(Objects.requireNonNull(
                tags, "tags are required"));
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

    public Set<String> getTags() {
        return tags;
    }

    public LogEntry withTag(final String tag) {
        Objects.requireNonNull(tag, "tag is required");
        Set<String> updateTags = new HashSet<>(this.tags);
        updateTags.add(tag);
        return new LogEntry(
                this.timestamp,
                this.sourceIp,
                this.username,
                this.eventType,
                this.port,
                updateTags);
    }

    private static int validatePort(final int port) {
        if (port < 0 || port > MAX_PORT) {
            throw new IllegalArgumentException(
                    "port must be between 0 and " + MAX_PORT);
        }
        return port;
    }
}
