package duke.task;

public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    public int getEquivalentNumber() {
        if (this.priority.equals("high")) {
            return 1;
        } else if (this.priority.equals("medium")) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public String toString() {
        return this.priority;
    }
}
