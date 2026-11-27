package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

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
}
