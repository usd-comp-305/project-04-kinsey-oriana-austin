package edu.sandiego.comp305;

import java.util.HashSet;

import java.util.Set;

public class EntityProfile {

    private int failedLogins;

    private final Set<Integer> portsTouched = new HashSet<>();

    private static final int MAX_PORT = 65535;

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
        return portsTouched;
    }

    public int getRiskScore() {
        return failedLogins;
    }
}
