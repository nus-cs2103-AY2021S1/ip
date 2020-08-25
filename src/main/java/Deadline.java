public class Deadline extends Task {

    public Deadline(String task, String duration) {
        super(task, Tasktype.DEADLINE, duration);
    }

    public Deadline(String task, String duration, boolean isDone) {
        super(task, Tasktype.EVENT, duration, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.duration);

    }

}
