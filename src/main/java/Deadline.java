class Deadline extends Task {
    String deadline;

    Deadline(String name, String deadline) {
        super(name, Type.DEADLINE);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
