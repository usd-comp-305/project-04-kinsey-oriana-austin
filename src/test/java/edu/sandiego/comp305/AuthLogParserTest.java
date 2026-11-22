package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class AuthLogParserTest {

    private static final String VALID_LINE =
            "2026-12-01T10:15:30Z 10.0.0.5 alice LOGIN_FAILURE 22";

    private final LogParser parser = new AuthLogParser();

    @Test
    void parsesTimestamp() {
        final LogEntry entry = parser.parse(VALID_LINE).orElseThrow();

        assertEquals(Instant.parse("2026-12-01T10:15:30Z"),
                entry.getTimestamp());
    }

    @Test
    void parsesSourceIp() {
        final LogEntry entry = parser.parse(VALID_LINE).orElseThrow();

        assertEquals("10.0.0.5", entry.getSourceIp());
    }

    @Test
    void parsesUsername() {
        final LogEntry entry = parser.parse(VALID_LINE).orElseThrow();

        assertEquals("alice", entry.getUsername());
    }

    @Test
    void parseLoginSuccessEventType() {
        final LogEntry entry = parser.parse(
                "2026-12-01T10:15:30Z 10.0.0.5 alice LOGIN_SUCCESS 22")
                .orElseThrow();
        assertEquals(EventType.LOGIN_SUCCESS, entry.getEventType());
    }

    @Test
    void parsesPort() {
        final LogEntry entry = parser.parse(VALID_LINE).orElseThrow();

        assertEquals(22, entry.getPort());
    }

    @Test
    void treatsDashUsernameAsEmpty() {
        final LogEntry entry = parser.parse(
                "2026-12-01T10:15:30Z 10.0.0.5 - CONNECTION_ATTEMPT 22"
        ).orElseThrow();

        assertEquals("", entry.getUsername());
    }
}