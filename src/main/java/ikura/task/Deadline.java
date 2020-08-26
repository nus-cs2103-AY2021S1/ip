// Deadline.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.InvalidInputException;

/**
 * A class representing a Deadline task. It has a description (name), and a deadline (date).
 */
public class Deadline extends Task {

    private final LocalDate deadline;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param name     the Deadline's description.
     * @param deadline the deadline of this task.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return the deadline.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {

        return String.format("[D] %s (by: %s)", super.toString(),
            this.deadline.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Deadline)
            && ((Deadline) other).getName().equals(this.getName())
            && ((Deadline) other).getDeadline().equals(this.getDeadline());
    }

    /**
     * Parses a Deadline from the given input. The input should be of the
     * form "deadline <description> /by <date>", where <date> is in the form
     * "yyyy-mm-dd".
     *
     * @param  input the user's input.
     * @return the Deadline with the given description and deadline.
     * @throws InvalidInputException if the input was malformed.
     */
    public static Deadline parse(String input) throws InvalidInputException {

        var parts = DatedTask.parse("deadline", input, "by", getUsage());
        return new Deadline(parts.fst(), DatedTask.parseDate(parts.snd()));
    }

    /**
     * Gets the usage of the deadline command; this is the expected format of the input
     * passed to the parse() method.
     *
     * @return the usage.
     */
    private static String getUsage() {
        return "deadline <description> /by <date>";
    }
}
