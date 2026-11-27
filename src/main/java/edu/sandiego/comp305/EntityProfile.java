package edu.sandiego.comp305;

import java.util.HashSet;

import java.util.Set;

public class EntityProfile {

    private int failedLogins;

    private final Set<Integer> portsTouched = new HashSet<>();

    public void recordFailedLogin() {
        failedLogins++;
    }

    public void recordPort(final int port) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public Set<Integer> getPortsTouched() {
        return portsTouched;
    }

    public int getRiskScore() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
