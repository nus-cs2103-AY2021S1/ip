package service;

import exceptions.InvalidCommandException;

public abstract class Task {
    String[] tokens;
    private boolean isDone;

    public Task(String[] tokens) {
        this.isDone = false;
        this.tokens = tokens;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    ///this should calculate description
    public abstract void parse() throws InvalidCommandException;

    public abstract String getDescription();

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }
}
