package duke.task;

public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private String type;

    private TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
