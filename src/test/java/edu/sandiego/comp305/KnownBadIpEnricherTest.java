package edu.sandiego.comp305;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class KnownBadIpEnricherTest {

    @Test
    void safeIpPassesThroughUnchanged() {
        final KnownBadIpEnricher enricher =
                new KnownBadIpEnricher(Set.of("192.168.1.50"));
        final LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "10.0.0.1",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        final LogEntry result = enricher.enrich(entry);

        assertSame(entry, result);
        assertTrue(result.getTags().isEmpty());
    }

    @Test
    void knownBadIpAddsTagAndPreservesOriginalFields() {
        final KnownBadIpEnricher enricher =
                new KnownBadIpEnricher(Set.of("192.168.1.50"));
        final Instant timestamp = Instant.parse("2026-01-01T00:00:00Z");
        final LogEntry entry = new LogEntry(
                timestamp,
                "192.168.1.50",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        final LogEntry result = enricher.enrich(entry);

        assertTrue(result.getTags().
                contains(KnownBadIpEnricher.KNOWN_BAD_IP_TAG));
        assertEquals(timestamp, result.getTimestamp());
        assertEquals("192.168.1.50", result.getSourceIp());
        assertEquals("alice", result.getUsername());
        assertEquals(EventType.LOGIN_FAILURE, result.getEventType());
        assertEquals(22, result.getPort());
    }

    @Test
    void emptyBadIpListLeavesEntryUnflagged() {
        final KnownBadIpEnricher enricher =
                new KnownBadIpEnricher(Set.of());
        final LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "192.168.1.50",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        final LogEntry result = enricher.enrich(entry);

        assertTrue(result.getTags().isEmpty());
    }

    @Test
    void constructorRejectsNullSet() {
        assertThrows(NullPointerException.class,
                ()-> new KnownBadIpEnricher(null));
    }

    @Test
    void enrichRejectsNullEntry() {
        final KnownBadIpEnricher enricher =
                new KnownBadIpEnricher(Set.of("192.168.1.50"));
        assertThrows(NullPointerException.class, ()-> enricher.enrich(null));
    }

    @Test
    void externalMutationOfInputSetDoesNotAffectEnricher() {
        final Set<String> mutableIps = new HashSet<>();
        mutableIps.add("192.168.1.50");
        final KnownBadIpEnricher enricher =
                new KnownBadIpEnricher(mutableIps);

        mutableIps.add("10.0.0.99");

        final LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "10.0.0.99",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        assertTrue(enricher.enrich(entry).getTags().isEmpty());
    }
}
