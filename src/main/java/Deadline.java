public class Deadline extends DatedTask {
    public Deadline(String name, String deadline) {
        super(name, deadline);
    }

    public Deadline(String name, boolean completed, String deadline) {
        super(name, completed, deadline);
    }

    @Override
    public String format() {
        return "D" + SAVE_DELIMITER + super.format();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
