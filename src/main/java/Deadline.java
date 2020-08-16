public class Deadline extends Task{

    private final String deadline;

    protected Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                super.getTask(),
                deadline);
    }

}
