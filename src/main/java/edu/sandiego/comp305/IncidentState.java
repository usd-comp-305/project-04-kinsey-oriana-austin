package edu.sandiego.comp305;

public interface IncidentState {

    public abstract IncidentState next(Incident incident);

    public abstract boolean allowsResponse();

    public abstract String getName();
}
