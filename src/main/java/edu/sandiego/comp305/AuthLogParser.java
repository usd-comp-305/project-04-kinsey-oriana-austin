package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Optional;

public class AuthLogParser implements LogParser {

    private static final int TIMESTAMP_INDEX = 0;

    private static final int IP_INDEX = 1;

    private static final int USER_INDEX = 2;

    private static final int TYPE_INDEX = 3;

    private static final int PORT_INDEX = 4;

    @Override
    public Optional<LogEntry> parse(final String line) {
        final String[] fields = line.split(" ");
        return Optional.of(new LogEntry(
                Instant.parse(fields[TIMESTAMP_INDEX]),
                fields[IP_INDEX],
                fields[USER_INDEX],
                EventType.valueOf(fields[TYPE_INDEX]),
                Integer.parseInt(fields[PORT_INDEX])
        ));
    }
}
