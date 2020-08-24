public class Event extends Task{

    private String task;
    private String time;

    protected Event(String task, String time) {
        this.task = task;
        this.time = time;
    }

    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                getTask(),
                time);
    }

}
