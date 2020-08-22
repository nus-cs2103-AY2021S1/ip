package service;

import exceptions.InvalidCommandException;
import parser.Flag;

import java.util.Map;

/**
 * A class represents a Event task
 */
public class EventTask extends Task {
    public static final String taskWord = "event";

    private String description;
    private String time;

    public EventTask(String[] tokens) {
        super(tokens, taskWord);
    }

    /**
     * Overriden method, to explicitly parse the task
     * @throws InvalidCommandException when its synax has problems, and to report to users
     */
    @Override
    public void parse() throws InvalidCommandException {
        Map<Flag, String> flags = Flag.parseFlags(tokens);

        if (!flags.containsKey(Flag.AT)) {
            throw new InvalidCommandException("Flag \\at not found");
        }

        if (!flags.containsKey(Flag.NONFLAG)) {
            throw new InvalidCommandException("Need description");
        }

        this.description = flags.get(Flag.NONFLAG);
        this.time = flags.get(Flag.AT);
    }

    /**
     * Get the description of the task
     * @return a string denoting the description
     */
    @Override
    public String getDescription() {
        return this.description + " (at" + this.time + ")";
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.getDescription();
    }
}
