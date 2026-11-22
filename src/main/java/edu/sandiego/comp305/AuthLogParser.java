package edu.sandiego.comp305;

import java.time.Instant;
import java.util.Optional;

public class AuthLogParser implements LogParser {

    @Override
    public Optional<LogEntry> parse(final String line) {
        final String[] fields = line.split(" ");
        return Optional.of(new LogEntry(
                Instant.parse(fields[0]),
                fields[1],
                fields[2],
                EventType.valueOf(fields[3]),
                0
        ));
    }
}
