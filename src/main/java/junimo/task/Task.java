package junimo.task;

/**
 * Represents an abstract Task object. Inherited by Deadline, Event and Todo classes.
 */
public abstract class Task {
    protected String description;
    protected Boolean isDone;

    private enum TaskType {
        DEADLINE("deadline <task description> /by <yyyy-MM-dd>"),
        EVENT("event <event description> /start <yyyy-MM-dd HH:mm> /end <yyyy-MM-dd HH:mm>"),
        TODO("todo <task description>");

        private final String format;

        TaskType(String format) {
            this.format = format;
        }

        String getFormat() {
            return this.format;
        }
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns format of command to add a specific task type.
     * @param taskTypeString String indicating task type (event, deadline or todo).
     * @return Format of command to add task of type as specified by taskTypeString.
     */
    public static String getFormat(String taskTypeString) {
        return TaskType.valueOf(taskTypeString.toUpperCase()).getFormat();
    }

    /**
     * Returns task in String format that is suitable for saving to and retrieving from hard disk.
     * @return String of task in format for saving to and retrieving from hard disk.
     */
    public abstract String getParsedTask();

    /**
     * Returns checkbox with tick/cross representing if task is done (tick) or not (cross).
     * @return String representing checkbox with unicode character CHECK MARK (U+2713) if task is done
     * or HEAVY BALLOT X (U+2718) if task is not done.
     */
    public String getCheckBox() {
        if (isDone) {
            return "[\u2713]";
        } else {
            return "[\u2718]";
        }
    }

    /**
     * Marks this task as done.
     */
    public void setDoneAsTrue() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getCheckBox() + " " + description;
    }

    /**
     * Overrides Object equals method.
     * @param other Object compared to.
     * @return True if other is also a Task object with the same description and isDone fields. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Task) {
            Task otherTask = (Task) other;
            return description.equals(otherTask.description) && isDone == otherTask.isDone;
        } else {
            return false;
        }
    }
}
