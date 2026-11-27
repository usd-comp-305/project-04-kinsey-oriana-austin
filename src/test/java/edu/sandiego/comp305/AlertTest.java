package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class AlertTest {
    private static final String RULE_NAME = "BruteForce";
    private static final Severity SEVERITY = Severity.HIGH;
    private static final String ENTITY = "10.0.0.5";
    private static final Instant TIME =
            Instant.parse("2026-12-01T10:15:30Z");

    @Test
    void returnCorrectRuleName() {
        final Alert alert = new Alert(RULE_NAME, SEVERITY, ENTITY, TIME);
        assertEquals(RULE_NAME, alert.getRuleName());
    }
}
