/**
 * Encapsulates Tasks that are to be completed.
 * Object only carries information about the task to be completed.
 */
public class ToDo extends Task {

    /** Symbol representing the type of Task this is */
    protected static final String SYMBOL = "T";

    /**
     * Creates a ToDo object with the given task description.
     * @param taskDescription description of the task to be done
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns the String representation of the object.
     * Updated from the superclass Task to include the type of Task this object is.
     * @return string representing the ToDo task with a "[T]" identifier
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }

    /**
     * Returns a summary of the ToDo.
     * @return string containing type, completion status and description
     */
    @Override
    public String getSummary() {
        return String.format("%s|%d|%s",
                SYMBOL,
                (isCompleted() ? 1 : 0),
                description);
    }

    /**
     * Returns a ToDo object corresponding to the summary given.
     * @param summary string summary of the ToDo object to be reconstructed
     * @return ToDo object representing the summary given
     */
    public static ToDo reconstructFromSummary(String summary) {
        String[] details = summary.split("\\|");
        if (details.length != 3) {
            throw new DukeException("Wrong number of details!");
        } else if (!(details[1].equals("1") || details[1].equals("0"))) {
            throw new DukeException("Invalid completion status! Ensure that it is either 0 or 1");
        }
        boolean isDone = details[1].equals("1");
        ToDo newToDo = new ToDo(details[2]);
        if (isDone) {
            newToDo.markDone();
        }
        return newToDo;
    }
}
