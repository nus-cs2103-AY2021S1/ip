/**
 * Encapsulates Tasks with deadlines.
 * Object carries information of the task to be completed and the deadline.
 */
public class Deadlines extends Task {

    /**
     * deadline of the task represented by a String
     */
    protected String deadLine;

    /**
     * String separator used to separate the task description from teh dateline
     */
    protected static final String SPLITTER = " /by ";

    /**
     * Creates a new Deadlines object from the full deadline description that includes the deadline.
     * @param taskDescription description of the Deadlines object with both the task and the deadline
     */
    public Deadlines(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        deadLine = taskDescription.split(SPLITTER)[1];
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of task and the deadline.
     * @return string representing a Deadlines task with a "[D]" identifier and the deadline at the back
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadLine);
    }





}
