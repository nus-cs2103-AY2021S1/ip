package tasks;

public class Deadline extends Task {

    // task must be done by this time
    private final String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    protected char getTaskType() {
        return 'D';
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
