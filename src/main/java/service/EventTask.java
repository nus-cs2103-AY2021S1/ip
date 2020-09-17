package service;

import java.util.Map;

import exceptions.InvalidCommandException;
import parser.Flag;


/**
 * A class represents a Event task
 */
public class EventTask extends Task {
    public static final String TASK_WORD = "event";

    private String description;
    private String time;

    public EventTask(String[] tokens) {
        super(tokens, TASK_WORD);
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

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof EventTask)) {
            return false;
        }
        EventTask otherEvent = (EventTask) other;
        return (this.description.equals(otherEvent.description) && this.time.equals(otherEvent.time));
    }
}
