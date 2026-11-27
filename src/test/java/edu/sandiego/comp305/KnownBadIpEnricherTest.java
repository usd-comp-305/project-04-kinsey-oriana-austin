package edu.sandiego.comp305;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.Set;
import org.junit.jupiter.api.Test;

class KnownBadIpEnricherTest {

    @Test
    void safeIpPassesThroughUnchanged() {
        KnownBadIpEnricher enricher = new KnownBadIpEnricher(Set.of("192.168.1.50"));
        LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "10.0.0.1",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        LogEntry result = enricher.enrich(entry);

        assertSame(entry, result);
        assertTrue(result.getTags().isEmpty());
    }

    @Test
    void knownBadIpAddsTagAndPreservesOriginalFields() {
        KnownBadIpEnricher enricher = new KnownBadIpEnricher(Set.of("192.168.1.50"));
        Instant timestamp = Instant.parse("2026-01-01T00:00:00Z");
        LogEntry entry = new LogEntry(
                timestamp,
                "192.168.1.50",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        LogEntry result = enricher.enrich(entry);

        assertTrue(result.getTags().contains("KNOWN_BAD_IP"));
        assertEquals(timestamp, result.getTimestamp());
        assertEquals("192.168.1.50", result.getSourceIp());
        assertEquals("alice", result.getUsername());
        assertEquals(EventType.LOGIN_FAILURE, result.getEventType());
        assertEquals(22, result.getPort());
    }

    @Test
    void emptyBadIpListLeavesEntryUnflagged() {
        KnownBadIpEnricher enricher = new KnownBadIpEnricher(Set.of());
        LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "192.168.1.50",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        LogEntry result = enricher.enrich(entry);

        assertTrue(result.getTags().isEmpty());
    }
}