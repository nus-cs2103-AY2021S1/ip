public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private final String taskName;

    private TaskType(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
