package edu.sandiego.comp305;

public interface ResponseAction {

    public abstract void execute(Incident incident);

    public abstract void undo();

    public abstract String describe();
}