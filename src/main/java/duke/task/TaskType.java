package duke.task;

public enum TaskType {
    TODO("[T]"), DEADLINE("[D]"), EVENT("[E]");

    private final String bracketedTaskType;

    TaskType(String bracketedTaskType) {
        this.bracketedTaskType = bracketedTaskType;
    }

    @Override
    public String toString() {
        return bracketedTaskType;
    }
}
