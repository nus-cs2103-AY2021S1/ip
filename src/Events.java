public class Events extends Task {
    protected String at;

    public Events(String task, String at) {
        super(task);
        this.at = at;
    }

    public Events(String task, String at, boolean isDone) {
        super(task, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
