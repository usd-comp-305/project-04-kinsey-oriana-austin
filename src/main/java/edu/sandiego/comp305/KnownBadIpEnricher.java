package edu.sandiego.comp305;

public class KnownBadIpEnricher implements Enricher {

    @Override
    public LogEntry enrich(final LogEntry entry) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}