package duke.task;

enum TaskPriority {
    URGENT(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4);

    private final int priorityValue;

    TaskPriority(int value) {
        this.priorityValue = value;
    }

    public int getPriorityValue() {
        return this.priorityValue;
    }
}
