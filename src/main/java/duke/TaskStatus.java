package duke;

public enum TaskStatus {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static boolean hasTime(String status) {
        return status.equals("event") || status.equals("deadline");
    }

    public static TaskStatus valueOfStatus(String status) {
        for (TaskStatus e : values()) {
            if (e.status.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
