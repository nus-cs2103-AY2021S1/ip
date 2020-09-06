package duke.task;

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
     */
    public Deadline(String taskDescription) {
        int slash = taskDescription.indexOf("/");
        this.task = taskDescription.substring(0, slash);
        this.deadline = taskDescription.substring(slash + 4);
        this.done = false;
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
            deadline = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
