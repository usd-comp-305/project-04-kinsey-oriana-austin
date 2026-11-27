package edu.sandiego.comp305;

import java.util.Objects;
import java.util.Set;

public class KnownBadIpEnricher implements Enricher {
    public static final String KNOWN_BAD_IP_TAG = "KNOWN_BAD_IP";

    private final Set<String> badIps;

    public KnownBadIpEnricher(final Set<String> badIps) {
        this.badIps = Set.copyOf(Objects.requireNonNull(
                badIps, "badIps set is requried"));
    }

    @Override
    public LogEntry enrich(final LogEntry entry) {
        Objects.requireNonNull(entry, "entry is required");
        if(badIps.contains(entry.getSourceIp())) {
            return entry.withTag(KNOWN_BAD_IP_TAG);
        }
        return entry;
    }
}
