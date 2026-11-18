package edu.sandiego.comp305;

import java.util.List;

public class ImpossibleTravelRule implements DetectionRule {

    @Override
    public List<Alert> evaluate(
            final LogEntry entry,
            final ProfileStore profiles) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
