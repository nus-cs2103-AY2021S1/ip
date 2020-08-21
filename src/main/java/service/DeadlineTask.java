package service;

import exceptions.InvalidCommandException;
import parser.Flag;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.Map;

public class DeadlineTask extends Task {
    public static final String taskWord = "deadline";

    private String description;
    private String time;
    private LocalDate date;

    public DeadlineTask(String[] tokens) {
        super(tokens, taskWord);
    }

    @Override
    public void parse() throws InvalidCommandException {
        Map<Flag, String> flags = Flag.parseFlags(tokens);

        if (!flags.containsKey(Flag.BY)) {
            throw new InvalidCommandException("Flag /by not found");
        }

        if (!flags.containsKey(Flag.NONFLAG)) {
            throw new InvalidCommandException("Need description");
        }

        this.description = flags.get(Flag.NONFLAG);
        this.time = flags.get(Flag.BY);
        this.date = LocalDate.parse(this.time);
    }

    @Override
    public String getDescription() {
        return this.description + " (by " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.getDescription();
    }
}
