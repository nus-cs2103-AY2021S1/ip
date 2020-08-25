package duke.task;

import duke.datetime.DateTimeHandler;
import duke.DukeException;

import java.time.LocalDateTime;

/**
 * Encapsulates Tasks with deadlines.
 * Object carries information of the task to be completed and the deadline.
 */
public class Deadline extends Task {


    /** String separator used to separate the task description from the dateline */
    public static final String SPLITTER = " /by ";
    /** Symbol representing the type of Task this is */
    public static final String SYMBOL = "D";

    /** Deadline of the task */
    protected LocalDateTime deadline;

    /**
     * Creates a new Deadline object from the full deadline description that includes the deadline.
     * @param taskDescription description of the Deadline object with both the task and the deadline
     */
    public Deadline(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        String[] details = taskDescription.split(SPLITTER);
        if (details.length == 1) {
            throw new DukeException("Please specify a deadline!");
        } else if (details.length > 2) {
            throw new DukeException("Please follow the format of \"{task} /by {deadline}\"");
        }
        deadline = DateTimeHandler.parseDateTime(taskDescription.split(SPLITTER)[1]);
    }

    /**
     * Creates a new Deadline object by manually inputting t
     * @param deadlineDescription description of the task only
     * @param deadline string description of the deadline
     */
    private Deadline(String deadlineDescription, String deadline) {
        super(deadlineDescription);
        this.deadline = DateTimeHandler.parseDateTime(deadline);
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of task and the deadline.
     * @return string representing a Deadline task with a "[D]" identifier and the deadline at the back
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", SYMBOL, super.toString(),
                getTimingString());
    }

    /**
     * Adjusts the String forms of the start and end timing for output
     * @return string of the start and end timings
     */
    private String getTimingString() {
        return DateTimeHandler.STANDARD_DATETIME_FORMAT.format(deadline);
    }

    /**
     * Returns a summary of the Deadline.
     * @return string containing type, completion status, description and timing
     */
    @Override
    public String getSummary() {
        return String.format("%s|%d|%s|%s",
                SYMBOL,
                (isCompleted() ? 1 : 0),
                description,
                getTimingString());
    }

    /**
     * Returns an Deadline object corresponding to the summary given.
     * @param summary string summary of the Deadline object to be reconstructed
     * @return Deadline object representing the summary given
     */
    public static Deadline reconstructFromSummary(String summary) {
        String[] details = summary.split("\\|");
        if (details.length != 4) {
            throw new DukeException("Wrong number of details!");
        } else if (!(details[1].equals("1") || details[1].equals("0"))) {
            throw new DukeException("Invalid completion status! Ensure that it is either 0 or 1");
        }
        boolean isDone = details[1].equals("1") ? true : false;
        Deadline newDeadline = new Deadline(details[2], details[3]);
        if (isDone) {
            newDeadline.markDone();
        }
        return newDeadline;
    }





}
