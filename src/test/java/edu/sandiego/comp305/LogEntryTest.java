package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryTest {
    private final Instant TIME = Instant.parse("2026-12-01T10:15:30Z");

    private final String IP = "10.0.0.5";

    private final String USER = "alice";

    private final EventType TYPE = EventType.LOGIN_FAILURE;

    private static final int PORT = 22;

    @Test
    void returnsTimestampItWasCreatedWith() {
        final Instant time = Instant.parse("2026-12-01T10:15:30Z");

        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, PORT);

        assertEquals(time, entry.getTimestamp());
    }

    @Test
    void returnsSourceIpItWasCreatedWith() {
        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, PORT);
        assertEquals("10.0.0.5", entry.getSourceIp());
    }

    @Test
    void returnsUsernameItWasCreatedWith() {
        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, PORT);
        assertEquals("alice", entry.getUsername());
    }

    @Test
    void returnsEventTypeItWasCreatedWith() {
        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, PORT);
        assertEquals(EventType.LOGIN_FAILURE, entry.getEventType());
    }

    @Test
    void returnsPortItWasCreatedWith() {
        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, PORT);
        assertEquals(22, entry.getPort());
    }

    @Test
    void rejectsNullTimestamp() {
        assertThrows(
                NullPointerException.class,
                ()-> new LogEntry(null, IP, USER, TYPE, PORT));
    }

    @Test
    void rejectsNullSourceIp() {
        assertThrows(
                NullPointerException.class,
                ()-> new LogEntry(TIME, null, USER, TYPE, PORT));
    }

    @Test
    void rejectsNullUsername() {
        assertThrows(
                NullPointerException.class,
                ()-> new LogEntry(TIME, IP, null, TYPE, PORT));
    }

    @Test
    void rejectsNullEventType() {
        assertThrows(
                NullPointerException.class,
                ()-> new LogEntry(TIME, IP, USER, null, PORT));
    }

    @Test
    void rejectsPortAboveMaximum() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LogEntry(TIME, IP, USER, TYPE, 65536));
    }

    @Test
    void rejectsNegativePort() {
        assertThrows(
                IllegalArgumentException.class,
                ()-> new LogEntry(TIME, IP, USER, TYPE, -1));
    }

    @Test
    void acceptsLowestPort() {
        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, 0);
        assertEquals(0, entry.getPort());
    }

    @Test
    void acceptsHighestPort() {
        final LogEntry entry = new LogEntry(TIME, IP, USER, TYPE, 65535);
        assertEquals(65535, entry.getPort());
    }
}
