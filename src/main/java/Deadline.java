public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, boolean isCompleted, String deadline) {
        super(description,isCompleted);
        this.deadline = deadline;
    }
    public String getDeadline() {
        return this.deadline;
    }

    public String getTaskSymbol() {
        return "[D]";
    }

    public String toString () {
        return String.format("%s (by: %s)",this.description,this.deadline);
    }
}
