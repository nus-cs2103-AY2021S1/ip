package service;

import exceptions.InvalidCommandException;

/**
 * Mold of tasks.
 */
public abstract class Task {
    public String[] tokens;
    public String taskWord;
    public boolean isDone;

    /**
     * Constructor
     * @param tokens list of tokens
     * @param taskWord: either todo, event or deadline
     */
    public Task(String[] tokens, String taskWord) {
        this.isDone = false;
        this.tokens = tokens;
        this.taskWord = taskWord;
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
