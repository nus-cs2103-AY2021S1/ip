public class Deadline extends Task {
    private String date;

    Deadline(String taskName, boolean isCompleted, String date) {
        super(taskName, isCompleted);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}
