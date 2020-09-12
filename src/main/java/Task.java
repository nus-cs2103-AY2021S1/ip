/**
 * Encapsulates a task which other specific tasks extend from.
 */
public class Task {

    /**
     * Member variables holding name and status of task.
     */
    private String name;
    private boolean isDone;

    /**
     * Constructor to create a task.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Serializes the task into a string to be saved.
     *
     * @return Serialized string.
     */
    public String serialize() {
        return (isDone ? "1" : "0") + "|" + name;
    }

    /**
     * Retrieves name of task.
     *
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Parses a serialized string by checking the first letter for type.
     * Designates the parsing to the appropriate class.
     *
     * @param serial The serialized string.
     * @return The task object.
     */
    public static Task parse(String serial) {
        Character type = serial.charAt(0);
        String[] split = serial.split("\\|");
        switch (type) {
        case 'T':
            return Todo.parse(split);
        case 'E':
            return Event.parse(split);
        case 'D':
            return Deadline.parse(split);
        default:
            return null;
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "O" : "X") + "] " + name;
    }
}
