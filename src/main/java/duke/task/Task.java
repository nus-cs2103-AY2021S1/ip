package duke.task;

/**
 * Supports creation of duke.task.Task objects and duke.task.Task actions.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Creates a duke.task.Task object
     *
     * @param description  Description of task.
     * @param taskType Type of task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Creates a duke.task.Task object with extra parameter that defines whether
     * task is done or not.
     *
     * @param description Description of task.
     * @param taskType Type of task.
     * @param isDone Whether task is done or not.
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns a tick if isDone is true and a cross otherwise.
     *
     * @return Either a tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the task's isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if task is done or not.
     *
     * @return Either true or false.
     */
    public boolean isDone(){
        return isDone;
    }

    /**
     * Returns the type of task.
     *
     * @return Type of task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    public String buildSaveString() {
        String type = "";
        switch (this.taskType) {
            case TODO:
                type = "T";
                break;
            case DEADLINE:
                type = "D";
                break;
            case EVENT:
                type = "E";
                break;
        }
        String doneStatus = isDone ? "1" : "0";
        return type + "|" + doneStatus + "|" + this.description;
    }

    /**
     * Returns string representation of the duke.task.Task object.
     *
     * @return String representation of the duke.task.Task object.
     */
    @Override
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "]"
                + this.getDescription();
    }
}
