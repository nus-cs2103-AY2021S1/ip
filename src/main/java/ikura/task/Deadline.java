// Deadline.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.InvalidInputException;

/**
 * A class representing a Deadline task. It has a description (name), and a deadline (date).
 */
public class Deadline extends DatedTask {

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param name     the Deadline's description.
     * @param deadline the deadline of this task.
     */
    public Deadline(String name, LocalDate deadline) {
        this(name, "", deadline);
    }

    /**
     * Constructs a new Deadline task with the given title, description and date.
     *
     * @param title       the Deadline's description.
     * @param description the Deadline's description.
     * @param deadline    the date of this event.
     */
    public Deadline(String title, String description, LocalDate deadline) {
        super(title, description, deadline);
    }

    @Override
    public String toString() {

        return String.format("[D] %s (by: %s)", super.toString(),
            this.getDate().format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Deadline)
            && ((Deadline) other).getTitle().equals(this.getTitle())
            && ((Deadline) other).getDate().equals(this.getDate())
            && ((Deadline) other).getDescription().equals(this.getDescription());
    }

    /**
     * Parses a Deadline from the given input. The input should be of the
     * form "deadline (description) /by (date)", where (date) is in the form
     * "yyyy-mm-dd".
     *
     * @param  input the user's input.
     * @return the Deadline with the given description and deadline.
     * @throws InvalidInputException if the input was malformed.
     */
    public static Deadline parse(String input) throws InvalidInputException {

        var desc = TaskParser.parse("deadline", input, "by", /* dateCompulsory: */ true, getUsage());
        assert desc.hasTitle() && desc.hasDate();

        if (desc.hasDescription()) {
            return new Deadline(desc.getTitle().get(), desc.getDescription().get(), desc.getDate().get());
        } else {
            return new Deadline(desc.getTitle().get(), desc.getDate().get());
        }
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
