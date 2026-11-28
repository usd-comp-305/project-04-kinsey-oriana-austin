package edu.sandiego.comp305;

import java.util.HashSet;

import java.util.Set;

public class EntityProfile {

    private static final int MAX_PORT = 65535;

    private int failedLogins;

    private final Set<Integer> portsTouched = new HashSet<>();

    public void recordFailedLogin() {
        failedLogins++;
    }

    public void recordPort(final int port) {
        if (port < 0 || port > MAX_PORT) {
            throw new IllegalArgumentException("invalid port");
        }

        portsTouched.add(port);
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public Set<Integer> getPortsTouched() {
        return Set.copyOf(portsTouched);
    }

    public int getRiskScore() {
        return failedLogins + portsTouched.size();
    }
}
