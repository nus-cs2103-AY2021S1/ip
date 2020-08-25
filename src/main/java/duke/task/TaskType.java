package duke.task;

public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private String taskName;

    TaskType(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
