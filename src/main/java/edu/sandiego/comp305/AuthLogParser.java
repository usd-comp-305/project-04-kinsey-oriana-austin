package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Optional;

public class AuthLogParser implements LogParser {

    private static final int TIMESTAMP_INDEX = 0;

    private static final int IP_INDEX = 1;

    private static final int USER_INDEX = 2;

    private static final int TYPE_INDEX = 3;

    private static final int PORT_INDEX = 4;

    private static final String ABSENT = "-";

    @Override
    public Optional<LogEntry> parse(final String line) {
        if (line == null || line.isBlank()) {
            return Optional.empty();
        }
        final String[] fields = line.split(" ");
        return Optional.of(new LogEntry(
                Instant.parse(fields[TIMESTAMP_INDEX]),
                fields[IP_INDEX],
                username(fields[USER_INDEX]),
                EventType.valueOf(fields[TYPE_INDEX]),
                port(fields[PORT_INDEX])
        ));
    }

    private String username(final String field) {
        if (ABSENT.equals(field)) {
            return "";
        }
        return field;
    }

    private int port(final String field) {
        if (ABSENT.equals(field)) {
            return 0;
        }
        return Integer.parseInt(field);
    }
}
