package edu.sandiego.comp305;

import java.util.Set;

public class EntityProfile {

    private int failedLogins;

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
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int getRiskScore() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
