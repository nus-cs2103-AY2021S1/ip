/**
 * Encapsulates Tasks with deadlines.
 * Object carries information of the task to be completed and the deadline.
 */
public class Deadline extends Task {


    /** String separator used to separate the task description from the dateline */
    public static final String SPLITTER = " /by ";
    /** Symbol representing the type of Task this is */
    protected static final String SYMBOL = "D";

    /** Deadline of the task represented by a String */
    protected String deadline;

    /**
     * Creates a new Deadline object from the full deadline description that includes the deadline.
     * @param taskDescription description of the Deadline object with both the task and the deadline
     */
    public Deadline(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        deadline = taskDescription.split(SPLITTER)[1];
    }

    /**
     * Creates a new Deadline object by manually inputting t
     * 1
     * @param deadlineDescription
     * @param deadline
     */
    private Deadline(String deadlineDescription, String deadline) {
        super(deadlineDescription);
        this.deadline = deadline;
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of task and the deadline.
     * @return string representing a Deadline task with a "[D]" identifier and the deadline at the back
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", SYMBOL, super.toString(), deadline);
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
                deadline);
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
        return new Deadline(details[2], details[3]);
    }





}
