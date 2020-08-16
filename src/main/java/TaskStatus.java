public enum TaskStatus {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    public final String status;

    TaskStatus(String status) {
        this.status = status;
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
