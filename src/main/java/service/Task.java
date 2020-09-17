package service;

import exceptions.InvalidCommandException;

/**
 * Mold of tasks.
 */
public abstract class Task {
    protected String[] tokens;
    protected String taskWord;
    protected boolean isDone;

    /**
     * Constructor
     * @param tokens list of {@token}
     * @param taskWord either todo, event or deadline, denoting {@taskWord}
     */
    public Task(String[] tokens, String taskWord) {
        this.isDone = false;
        this.tokens = tokens;
        this.taskWord = taskWord;
    }

    public String[] getTokens() {
        return this.tokens;
    }

    public String getTaskWord() {
        return this.taskWord;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Mark this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Explicitly parse the task from tokens
     * @throws InvalidCommandException if the tokens are invalid
     */
    ///this should calculate description
    public abstract void parse() throws InvalidCommandException;

    /**
     * Get task descripton
     * @return description
     */
    public abstract String getDescription();

    /**
     * Either done or undone
     * @return a cute string
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }
}
