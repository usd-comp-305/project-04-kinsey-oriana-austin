package edu.sandiego.comp305;

import java.util.List;

public interface DetectionRule {

    public abstract List<Alert> evaluate(LogEntry entry, ProfileStore profiles);

    public abstract String getName();
}