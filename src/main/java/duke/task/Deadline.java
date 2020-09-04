package duke.task;

import java.time.LocalDateTime;

import duke.Duke;
import duke.DukeException;
import duke.InvalidSaveException;
import duke.datetime.DateTimeHandler;

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
    private LocalDateTime deadline;

    /**
     * Creates a new Deadline object from the full deadline description that includes the deadline.
     * @param taskDescription description of the Deadline object with both the task and the deadline
     * @throws DukeException if the string provided to create the deadline is invalid
     */
    public Deadline(String taskDescription) throws DukeException {
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
     * @throws DukeException if the deadline provided has an invalid datetime format
     */
    private Deadline(String deadlineDescription, String deadline) throws DukeException {
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
                isCompleted() ? 1 : 0,
                getTaskDescription(),
                getTimingString());
    }

    /**
     * Returns an Deadline object corresponding to the summary given.
     * @param summary string summary of the Deadline object to be reconstructed
     * @return Deadline object representing the summary given
     * @throws InvalidSaveException if the summary in the save file is invalid
     */
    public static Deadline reconstructFromSummary(String summary) throws InvalidSaveException {
        String[] details = summary.split("\\|");
        if (details.length != 4) {
            throw new InvalidSaveException("Wrong number of details!");
        } else if (!(details[1].equals("1") || details[1].equals("0"))) {
            throw new InvalidSaveException("Invalid completion status! Ensure that it is either 0 or 1");
        }
        boolean isDone = details[1].equals("1") ? true : false;
        try {
            Deadline newDeadline = new Deadline(details[2], details[3]);
            if (isDone) {
                newDeadline.markDone();
            }
            return newDeadline;
        } catch (DukeException e) {
            throw new InvalidSaveException("Invalid datetime format in save!");
        }
    }





}
