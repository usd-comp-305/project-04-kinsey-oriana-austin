package edu.sandiego.comp305;

public class ResolvedState implements IncidentState {

    @Override
    public IncidentState next(final Incident incident) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean allowsResponse() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
