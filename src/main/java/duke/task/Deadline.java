package duke.task;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents a deadline task
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * creates a new deadline task based on the given description
     * @param taskDescription the full description of the deadline task in the following format:
     *                        "deadline deadline_task_description /by due_date"
     * @throws EmptyDescriptionException if the description given is empty
     * @throws EmptyDateException if the date given is empty
     */
    public Deadline(String taskDescription) throws EmptyDescriptionException, EmptyDateException {
        if (taskDescription.length() <= 9) {
            throw new EmptyDescriptionException("oops! the description of a deadline cannot be empty");
        } else if (!taskDescription.contains("/")) {
            throw new EmptyDateException("oops! the date for the deadline was not specified");
        } else {
            int space = taskDescription.indexOf(" ");
            int slash = taskDescription.indexOf("/");

            this.task = taskDescription.substring(space + 1, slash);
            this.deadline = taskDescription.substring(slash + 4);
            this.done = false;
        }
    }

    /**
     * returns a string representation of the deadline task
     * @return string representation of the deadline task
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String deadline;
        try {
            LocalDate localDate = LocalDate.parse(this.deadline);
            deadline = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            deadline = this.deadline;
        }

        sb.append("[D]")
                .append(super.toString())
                .append("(by: ").append(deadline).append(")");
        return sb.toString();
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
                this.deadline;
    }

    /**
     * decodes a given line of text and transforms it into a deadline task
     * @param string the line of text to decode
     * @return the deadline task that has been decoded from the given input
     * @throws EmptyDescriptionException if the description given is empty
     * @throws EmptyDateException if the date given is empty
     */
    public static Deadline decode(String string) throws EmptyDescriptionException, EmptyDateException {
        String[] split = string.split(" \\| ");

        String taskDescription = "deadline " + split[2] + " /by " + split[3];

        Deadline deadline = new Deadline(taskDescription);

        if (split[1].contains("1")) {
            deadline.markAsDone();
        }

        return deadline;
    }
}
