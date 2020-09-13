package duke.tasks;

public enum Priority {
    HIGH,
    MID,
    LOW,
    NONE;

    @Override
    public String toString() {
        return this.name();
    }
}
