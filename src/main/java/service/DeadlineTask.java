package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import exceptions.InvalidCommandException;
import parser.Flag;



/**
 * A class represents a Deadline task
 */
public class DeadlineTask extends Task {
    public static final String TASK_WORD = "deadline";

    private String description;
    private String time;
    private LocalDate date;

    /**
     * Constructor
     * @param tokens an array of tokens starting with task name: todo meet Chau
     */
    public DeadlineTask(String[] tokens) {
        super(tokens, TASK_WORD);
    }

    /**
     * Overriden method, to explicitly parse the task
     * @throws InvalidCommandException when its synax has problems, and to report to users
     */
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

    /**
     * Get the description of the task
     * @return a string denoting the description
     */
    @Override
    public String getDescription() {
        return this.description + " (by " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.getDescription();
    }
}
