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

}