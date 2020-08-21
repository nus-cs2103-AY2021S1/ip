public class Deadline extends Task {

    String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String getInfo() {
        return String.format("\nD | %d | %s | %s", isDone ? 1 : 0, taskName, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}
