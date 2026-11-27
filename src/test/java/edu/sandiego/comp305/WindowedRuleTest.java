package edu.sandiego.comp305;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class WindowedRuleTest {

    private WindowedRule createRule(
            final int windowSeconds,
            final int threshold,
            final boolean triggered) {
        return new WindowedRule(windowSeconds, threshold) {
            @Override
            protected boolean isTriggered(
                    final LogEntry entry, final ProfileStore profiles) {
                return triggered;
            }

            @Override
            protected Alert buildAlert(final LogEntry entry) {
                return new Alert(
                        getName(),
                        Severity.MEDIUM,
                        entry.getSourceIp(),
                        entry.getTimestamp());
            }

            @Override
            public String getName() {
                return "TestRule";
            }
        };
    }

    @Test
    void belowThresholdReturnsEmptyList() {
        final WindowedRule rule = createRule(60, 3, true);
        final ProfileStore profiles = new ProfileStore();
        final Instant base = Instant.parse("2026-01-01T00:00:00Z");

        final LogEntry first = new LogEntry(
                base, "10.0.0.1", "alice", EventType.LOGIN_FAILURE, 22);
        final LogEntry second = new LogEntry(
                base.plusSeconds(10),
                "10.0.0.1",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        assertTrue(rule.evaluate(first, profiles).isEmpty());
        assertTrue(rule.evaluate(second, profiles).isEmpty());
    }

    @Test
    void reachingThresholdWithinWindowTriggersAlert() {
        final WindowedRule rule = createRule(60, 3, true);
        final ProfileStore profiles = new ProfileStore();
        final Instant base = Instant.parse("2026-01-01T00:00:00Z");

        rule.evaluate(new LogEntry(
                        base, "10.0.0.1", "alice", EventType.LOGIN_FAILURE, 22),
                profiles);
        rule.evaluate(new LogEntry(
                        base.plusSeconds(20),
                        "10.0.0.1",
                        "alice",
                        EventType.LOGIN_FAILURE,
                        22),
                profiles);

        final List<Alert> alerts = rule.evaluate(new LogEntry(
                        base.plusSeconds(40),
                        "10.0.0.1",
                        "alice",
                        EventType.LOGIN_FAILURE,
                        22),
                profiles);

        assertEquals(1, alerts.size());
        assertEquals("TestRule", alerts.get(0).getRuleName());
    }

    @Test
    void expiredEventsOutsideWindowDoNotTriggerAlert() {
        final WindowedRule rule = createRule(60, 3, true);
        final ProfileStore profiles = new ProfileStore();
        final Instant base = Instant.parse("2026-01-01T00:00:00Z");

        rule.evaluate(new LogEntry(
                        base, "10.0.0.1", "alice", EventType.LOGIN_FAILURE, 22),
                profiles);
        rule.evaluate(new LogEntry(
                        base.plusSeconds(10),
                        "10.0.0.1",
                        "alice",
                        EventType.LOGIN_FAILURE,
                        22),
                profiles);

        final List<Alert> alerts = rule.evaluate(new LogEntry(
                        base.plusSeconds(70),
                        "10.0.0.1",
                        "alice",
                        EventType.LOGIN_FAILURE,
                        22),
                profiles);

        assertTrue(alerts.isEmpty());
    }

    @Test
    void nonMatchingEventsAreIgnored() {
        final WindowedRule rule = createRule(60, 1, false);
        final ProfileStore profiles = new ProfileStore();
        final LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "10.0.0.1",
                "alice",
                EventType.LOGIN_SUCCESS,
                22);

        assertTrue(rule.evaluate(entry, profiles).isEmpty());
    }

    @Test
    void tracksEntitiesIndependently() {
        final WindowedRule rule = createRule(60, 2, true);
        final ProfileStore profiles = new ProfileStore();
        final Instant base = Instant.parse("2026-01-01T00:00:00Z");

        rule.evaluate(new LogEntry(
                        base, "10.0.0.1", "alice", EventType.LOGIN_FAILURE, 22),
                profiles);
        final List<Alert> alerts = rule.evaluate(new LogEntry(
                        base.plusSeconds(5),
                        "10.0.0.2",
                        "bob",
                        EventType.LOGIN_FAILURE,
                        22),
                profiles);

        assertTrue(alerts.isEmpty());
    }

    @Test
    void rejectsInvalidWindowOrThreshold() {
        assertThrows(
                IllegalArgumentException.class,
                () -> createRule(0, 3, true));
        assertThrows(
                IllegalArgumentException.class,
                () -> createRule(60, 0, true));
    }

    @Test
    void evaluateRejectsNullArguments() {
        final WindowedRule rule = createRule(60, 3, true);
        final ProfileStore profiles = new ProfileStore();
        final LogEntry entry = new LogEntry(
                Instant.parse("2026-01-01T00:00:00Z"),
                "10.0.0.1",
                "alice",
                EventType.LOGIN_FAILURE,
                22);

        assertThrows(
                NullPointerException.class,
                () -> rule.evaluate(null, profiles));
        assertThrows(
                NullPointerException.class,
                () -> rule.evaluate(entry, null));
    }
}
