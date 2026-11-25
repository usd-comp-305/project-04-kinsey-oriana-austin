package edu.sandiego.comp305;

import java.util.HashSet;
import java.util.Set;

public class BlockList {

    private final Set<String> blockedIps = new HashSet<>();

    public boolean isBlocked(final String ip) {
        return blockedIps.contains(ip);
    }

    public void block(final String ip) {
        blockedIps.add(ip);
    }

}
