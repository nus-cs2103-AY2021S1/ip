package duke.task;

public enum TaskType {
    TASK,
    TODO,
    EVENT,
    DEADLINE;

    public String toSaveString() {
        return "duke.task." + this.toString().toLowerCase();
    }
}
