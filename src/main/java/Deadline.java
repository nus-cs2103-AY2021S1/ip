class Deadline extends Task {
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
        return String.format("%s (by: %s)", name, deadline);
    }
}
