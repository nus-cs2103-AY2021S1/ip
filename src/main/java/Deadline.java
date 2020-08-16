public class Deadline extends Task {

    String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        if (done) {
            return String.format ("[E][\u2713] %s (by: %s)", this.task, this.by);
        } else {
            return String.format ("[E][\u2718] %s (by: %s)", this.task, this.by);
        }
    }
}
