public class Deadline extends Task {
    protected String date;

    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + date + ")";
    }
}
