package edu.sandiego.comp305;

import java.util.Set;

public class KnownBadIpEnricher implements Enricher {
    private final Set<String> badIps;

    public KnownBadIpEnricher(final Set<String> badIps) {
        this.badIps = badIps;
    }

    @Override
    public LogEntry enrich(final LogEntry entry) {
        if(badIps.contains(entry.getSourceIp())) {
            return entry.withTag("KNOWN_BAD_IP");
        }
        return entry;
    }
}
