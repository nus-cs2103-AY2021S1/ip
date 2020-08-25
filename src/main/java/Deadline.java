public class Deadline extends Task {
    protected String date;

    public Deadline(String task, String date, boolean isCompleted) {
        super(task, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
