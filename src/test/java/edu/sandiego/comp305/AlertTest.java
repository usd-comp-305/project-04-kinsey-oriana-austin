package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void returnCorrectSeverity() {
        final Alert alert = new Alert(RULE_NAME, SEVERITY, ENTITY, TIME);
        assertEquals(SEVERITY, alert.getSeverity());
    }

    @Test
    void returnCorrectEntity() {
        final Alert alert = new Alert(RULE_NAME, SEVERITY, ENTITY, TIME);
        assertEquals(ENTITY, alert.getEntity());
    }

    @Test
    void returnCorrectTime() {
        final Alert alert = new Alert(RULE_NAME, SEVERITY, ENTITY, TIME);
        assertEquals(TIME, alert.getTime());
    }

    @Test
    void rejectNullRuleName() {
        assertThrows(NullPointerException.class, () ->
                new Alert(null, SEVERITY, ENTITY, TIME));
    }

    @Test
    void rejectNullSeverity() {
        assertThrows(NullPointerException.class, () ->
                new Alert(RULE_NAME, null, ENTITY, TIME));
    }

    @Test
    void rejectNullEntity() {
        assertThrows(NullPointerException.class, () ->
                new Alert(RULE_NAME, SEVERITY, null, TIME));
    }

    @Test
    void rejectNullTime() {
        assertThrows(NullPointerException.class, () ->
                new Alert(RULE_NAME, SEVERITY, ENTITY, null));
    }
}
