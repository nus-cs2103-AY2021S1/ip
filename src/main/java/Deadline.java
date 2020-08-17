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
    public String toString() {
        String tick = this.isDone() ? "[✓]" : "[✗]";
        return String.format("%s%s %s", SYMBOL, tick, name);
    }
}
