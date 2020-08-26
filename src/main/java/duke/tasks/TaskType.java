package duke.tasks;

public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private String type;

    public String getType() {
        return this.type;
    }

    TaskType(String type) {
        this.type = type;
    }

    public static TaskType valueToType(String value) {
        for (TaskType t : values()) {
            if (t.type.equals(value)) {
                return t;
            }
        }
        return null;
    }
}
