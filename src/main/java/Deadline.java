class Deadline extends Task {
    String deadline;

    Deadline(String name, String deadline) {
        super(name, Type.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
