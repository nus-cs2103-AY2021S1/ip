package shiro.task;

import shiro.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * represents a deadline task
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * creates a new deadline task based on the given description
     * @param taskDescription the full description of the deadline task in the following format:
     *                        "deadline deadline_task_description /by due_date"
     */
    public Deadline(String taskDescription) {
        int slash = taskDescription.indexOf("/");
        this.task = taskDescription.substring(0, slash);
        this.date = Parser.parseDate(taskDescription.substring(slash + 4));
        this.done = false;
    }

    /**
     * returns a string representation of the deadline task
     * @return string representation of the deadline task
     */
    public String toString() {
        return "[D]" + super.toString() +
                "(by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * encodes the deadline task to a more appropriate format for storage
     * @return the encoded version of the deadline task
     */
    @Override
    public String encode() {
        return "D | " +
                this.isDoneInt() + " | " +
                this.task + "| " +
                this.date;
    }

    /**
     * returns the due date of the deadline task as a LocalDate object
     * @return due date of the deadline task
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * decodes a given line of text and transforms it into a deadline task
     * @param string the line of text to decode
     * @return the deadline task that has been decoded from the given input
     */
    public static Deadline decode(String string) {
        String[] split = string.split(" \\| ");

        String taskDescription = split[2] + " /by " + split[3];

        Deadline deadline = new Deadline(taskDescription);

        if (split[1].contains("1")) {
            deadline.markAsDone();
        }

        return deadline;
    }
}
