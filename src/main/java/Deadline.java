public class Deadline extends Task{

    private final String time;

    protected Deadline(String description, String time, boolean isDone) {
        super(description, isDone, TaskType.DEADLINE, time);
        this.time = time;
    }

    protected Deadline(String description, String time) {
        super(description, false, TaskType.DEADLINE, time);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
