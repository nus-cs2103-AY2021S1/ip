/**
 * Represents a Task called ToDo.
 */
public class ToDo extends Task {
    ToDo(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;

    }
    /**
     * Return an empty String to indicate there is no date line/event date for this ToDo.
     *
     * @return An empty string.
     */
    public String getDate() { return ""; }

    /**
     * Return the type of Task.
     *
     * @return Type of task.
     */
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
