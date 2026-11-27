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
}