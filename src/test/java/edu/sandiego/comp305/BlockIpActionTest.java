package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockIpActionTest {

    private static final String IP = "10.0.0.5";

    private final BlockList blockList = new BlockList();

    private final BlockIpAction action = new BlockIpAction(blockList, IP);

    @Test
    void describeNamesTheBlockedIp() {
        assertEquals("Block IP 10.0.0.5", action.describe());
    }

    @Test
    void executeBlocksTheIp() {
        action.execute(new Incident());

        assertTrue(blockList.isBlocked(IP));
    }
}