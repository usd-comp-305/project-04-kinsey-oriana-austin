package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryTest {

    @Test
    void returnsTimestampItWasCreatedWith() {
        final Instant time = Instant.parse("2026-12-01T10:15:30Z");

        final LogEntry entry = new LogEntry(time, "10.0.0.5", "alice", EventType.LOGIN_FAILURE, 22);

        assertEquals(time, entry.getTimestamp());
    }

    @Test
    void returnsSourceIpItWasCreatedWith() {
        final LogEntry entry = new LogEntry(Instant.parse("2026-12-01T10:15:30Z"),
                "10.0.0.5", "alice", EventType.LOGIN_FAILURE, 22);
        assertEquals("10.0.0.5", entry.getSourceIp());
    }

    @Test
    void returnsUsernameItWasCreatedWith() {
        final LogEntry entry = new LogEntry(Instant.parse("2026-12-01T10:15:30Z"),
                "10.0.0.5", "alice", EventType.LOGIN_FAILURE, 22);
        assertEquals("alice", entry.getUsername());
    }

}