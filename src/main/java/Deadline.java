public class Deadline extends Task{
    protected String by;

    Deadline(String task, String date) {
        super(task);
        this.by = date;
    }

    public String getDate() {
        return by;
    }
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
