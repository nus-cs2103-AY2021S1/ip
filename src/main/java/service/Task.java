package service;

import exceptions.InvalidCommandException;

public abstract class Task {
    public String[] tokens;
    public String taskWord;
    public boolean isDone;

    public Task(String[] tokens, String taskWord) {
        this.isDone = false;
        this.tokens = tokens;
        this.taskWord = taskWord;
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
