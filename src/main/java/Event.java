public class Event extends Task{

    private final String time;

    protected Event(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                super.getTask(),
                time);
    }

}
