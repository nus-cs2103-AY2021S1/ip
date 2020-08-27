class Deadline extends Task {
    static String SYMBOL = "[D]";

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name, TaskType.Deadline);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String getFileString() {
        String status = this.isDone() ? "T" : "F";
        return String.format("%s~deadline %s /by %s\n", status, name, deadline);
    }

    @Override
    public String toString() {
        String tick = this.isDone() ? "[✓]" : "[✗]";
        return String.format("%s%s %s (by: %s)", SYMBOL, tick, name, deadline);
    }
}
