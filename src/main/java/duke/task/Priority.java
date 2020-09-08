package duke.task;

public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return this.priority;
    }
}
