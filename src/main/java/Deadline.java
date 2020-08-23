public class Deadline extends Task {
    private String by;

    /**
     * Creates a deadline task
     * @param task Task to be completed
     * @param by Time to complete by
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Deadline: " + super.getTask() +
                "(by: " + by + ")" + "\n";
    }
}
