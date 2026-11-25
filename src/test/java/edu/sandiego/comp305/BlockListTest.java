package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockListTest {
    private  static final String IP = "10.0.0.5";

    private  final BlockList blockList = new BlockList();

    @Test
    void notBlockedByDefault() {
        assertFalse(blockList.isBlocked(IP));
    }

    @Test
    void blockedAfterBlocking() {
        blockList.block(IP);

        assertTrue(blockList.isBlocked(IP));
    }

}
