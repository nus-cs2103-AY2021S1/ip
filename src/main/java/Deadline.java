/**
 * Tasks that need to be done before a specific time
 */

public class Deadline extends Task{
    private final String doBy;

    public Deadline(String description, boolean isDone, String doBy) {
        super(description, isDone);
        this.doBy = doBy;
    }

    public String getDoBy() {
        return this.doBy;
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[D][%s] %s (by: %s)", box, this.description, this.doBy);
    }
}
