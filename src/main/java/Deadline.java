public class Deadline extends Task {
    String description;

    Deadline(String task, String description) {
        super(task);
        this.description = description;
    }
    
    Deadline(String task, String description, boolean done) {
        super(task, done);
        this.description = description;
    }

    @Override
    public String toDataString() {
        return "D // " + (done ? "1": "0") + " // " + task + " // " + description;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.description + ")";
    }
}
