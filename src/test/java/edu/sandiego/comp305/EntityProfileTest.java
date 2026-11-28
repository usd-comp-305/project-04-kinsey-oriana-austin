package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import javax.swing.text.html.parser.Entity;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EntityProfileTest {
    private static final int PORT = 44;

    private static final int OTHER_PORT = 444;

    @Test
    void startWithZeroFailedLogins() {
        final EntityProfile profile = new EntityProfile();
        assertEquals(0, profile.getFailedLogins());
    }

    @Test
    void recordFailedLogin() {
        final EntityProfile profile = new EntityProfile();
        profile.recordFailedLogin();
        assertEquals(1, profile.getFailedLogins());
    }

    @Test
    void recordMultipleFailedLogins() {
        final EntityProfile profile = new EntityProfile();
        profile.recordFailedLogin();
        profile.recordFailedLogin();
        profile.recordFailedLogin();
        profile.recordFailedLogin();
        assertEquals(4, profile.getFailedLogins());
    }

    @Test
    void startWithEmptyPortsTouched() {
        final EntityProfile profile = new EntityProfile();
        assertTrue(profile.getPortsTouched().isEmpty());
    }

    @Test
    void recordPort() {
        final EntityProfile profile = new EntityProfile();
        profile.recordPort(PORT);
        assertEquals(Set.of(PORT), profile.getPortsTouched());
    }

    @Test
    void recordMultipleDifferentPorts() {
        final EntityProfile profile = new EntityProfile();
        profile.recordPort(PORT);
        profile.recordPort(OTHER_PORT);
        assertEquals(
                Set.of(PORT, OTHER_PORT),
                profile.getPortsTouched());
    }

    @Test
    void doesNotRecordDuplicatePorts() {
        final EntityProfile profile = new EntityProfile();
        profile.recordPort(PORT);
        profile.recordPort(PORT);
        profile.recordPort(PORT);
        profile.recordPort(PORT);
        assertEquals(Set.of(PORT), profile.getPortsTouched());
    }

    @Test
    void rejectNegativePorts() {
        final EntityProfile profile = new EntityProfile();
        assertThrows(IllegalArgumentException.class, () ->
                profile.recordPort(-1));
    }

    @Test
    void rejectPortsAboveItsMaximum() {
        final EntityProfile profile = new EntityProfile();
        assertThrows(IllegalArgumentException.class, () ->
                profile.recordPort(65536));
    }

    @Test
    void recordLowestPort() {
        final EntityProfile profile = new EntityProfile();
        profile.recordPort(0);
        assertEquals(Set.of(0), profile.getPortsTouched());
    }

    @Test
    void recordHighestPort() {
        final EntityProfile profile = new EntityProfile();
        profile.recordPort(65535);
        assertEquals(Set.of(65535), profile.getPortsTouched());
    }

    @Test
    void failedLoginIncreasesRiskScore() {
        final EntityProfile profile = new EntityProfile();
        profile.recordFailedLogin();
        assertEquals(1, profile.getRiskScore());
    }
}
