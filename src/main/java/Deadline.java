public class Deadline extends Task {
    protected String deadlineTime;

    public Deadline(String description, String date) {
        super(description);
        this.deadlineTime = date;
    }

    @Override
    public String toString() {
        return "D|" + super.toString() + "|" + deadlineTime;
    }
}
