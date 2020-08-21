package service;

import exceptions.InvalidCommandException;
import parser.Flag;

import java.util.Map;

public class DeadlineTask extends Task {
    public static final String taskWord = "deadline";

    private String description;
    private String time;

    public DeadlineTask(String[] tokens) {
        super(tokens, taskWord);
    }

    @Override
    public void parse() throws InvalidCommandException {
        Map<Flag, String> flags = Flag.parseFlags(tokens);

        if (!flags.containsKey(Flag.AT)) {
            throw new InvalidCommandException("Flag /at not found");
        }

        if (!flags.containsKey(Flag.NONFLAG)) {
            throw new InvalidCommandException("Need description");
        }

        this.description = flags.get(Flag.NONFLAG);
        this.time = flags.get(Flag.AT);
    }

    @Override
    public String getDescription() {
        return this.description + " (by" + this.time + ")";
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description;
    }
}
