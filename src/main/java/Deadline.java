public class Deadline extends Task {
    private String dueTime;

    Deadline(String name, String dueTime) {
        super(name);
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueTime);
    }
}
