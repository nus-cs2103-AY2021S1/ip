public class Event extends Task {

    public Event (String task, String duration) {
        super(task, Tasktype.EVENT, duration);
    }

    public Event (String task, String duration, boolean isDone) {
        super(task, Tasktype.EVENT, duration, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.duration);
    }

}

