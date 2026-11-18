package edu.sandiego.comp305;

import java.time.Instant;

public class ResponseManager implements IncidentListener {

    @Override
    public void onIncidentUpdated(final Incident incident) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void respondTo(final Incident incident) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void rollbackExpired(final Instant now) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
