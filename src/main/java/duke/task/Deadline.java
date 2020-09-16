package duke.task;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

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
    /** Number of separate fields in a deadline save summary */
    private static final int NUM_FIELDS_SUMMARY = 4;
    /** Number of separate fields required to create a deadline task */
    private static final int NUM_FIELDS_DESCRIPTION = 2;

    /** Deadline of the task */
    private LocalDateTime deadline;

    /**
     * Creates a new Deadline object from the full deadline description that includes the deadline.
     *
     * @param taskDescription description of the Deadline object with both the task and the deadline.
     * @throws DukeException if the string provided to create the deadline is invalid.
     */
    public Deadline(String taskDescription) throws DukeException {
        super(taskDescription.split(SPLITTER)[0]);
        String[] details = taskDescription.split(SPLITTER);
        if (details.length != NUM_FIELDS_DESCRIPTION) {
            throw new DukeException("Please follow the format of \"{task} /by {deadline}\"");
        }

        deadline = DateTimeHandler.parseDateTime(taskDescription.split(SPLITTER)[1]);
    }

    /**
     * Creates a new Deadline object by manually inputting description and the deadline.
     *
     * @param deadlineDescription description of the task only.
     * @param deadline string description of the deadline.
     * @throws DukeException if the deadline provided has an invalid datetime format.
     */
    protected Deadline(String deadlineDescription, String deadline) throws DukeException {
        super(deadlineDescription);
        this.deadline = DateTimeHandler.parseDateTime(deadline);
    }

    /** Constructs a Deadline object directly from the description and deadline provided */
    protected Deadline(String deadlineDescription, LocalDateTime deadlineTiming) {
        super(deadlineDescription);
        this.deadline = deadlineTiming;
    }

    /**
     * Returns the deadline.
     *
     * @return deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of task and the deadline.
     *
     * @return string representing a Deadline task with a "[D]" identifier and the deadline at the back.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", SYMBOL, super.toString(),
                getTimingString());
    }

    /** Returns a string of the deadline in the standard format */
    private String getTimingString() {
        return DateTimeHandler.STANDARD_DATETIME_FORMAT.format(deadline);
    }

    /**
     * Returns a summary of the Deadline.
     *
     * @return string containing type, completion status, description and timing.
     */
    @Override
    public String getSummary() {
        return super.getSummary() + SYMBOL_SEPARATOR + getTimingString();
    }

    /**
     * Returns an Deadline object corresponding to the summary given.
     * Deadline summary has to be of the form "D|{0 or 1 representing completion}|{description}|{deadline}".
     *
     * @param summary string summary of the Deadline object to be reconstructed.
     * @return Deadline object representing the summary given.
     * @throws InvalidSaveException if the summary in the save file is invalid.
     */
    public static Deadline reconstructFromSummary(String summary) throws InvalidSaveException {
        String[] details = summary.split(Pattern.quote(SYMBOL_SEPARATOR));
        if (details.length != NUM_FIELDS_SUMMARY) {
            throw new InvalidSaveException("Wrong number of details!");
        } else if (!isValidSaveSymbol(details[1])) {
            throw new InvalidSaveException("Invalid completion status! Ensure that it is either 0 or 1");
        }

        boolean isDone = details[1].equals(SYMBOL_DONE);
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

    /**
     * Returns a "D" representing Deadline type.
     *
     * @return string "D".
     */
    @Override
    public String getSymbol() {
        return SYMBOL;
    }





}
