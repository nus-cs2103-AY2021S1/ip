public class Deadline extends Task {
    String deadline;
    public Deadline(String description, boolean isDone) {
        super(description.split(" /by ")[0], isDone);
        System.out.println("new deadline");
        this.deadline = description.split(" /by ")[1];
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.deadline + ")";
    }
}
