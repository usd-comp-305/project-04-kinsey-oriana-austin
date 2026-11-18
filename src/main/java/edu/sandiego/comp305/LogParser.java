package edu.sandiego.comp305;

import java.util.Optional;

public interface LogParser {

    public abstract Optional<LogEntry> parse(String line);
}