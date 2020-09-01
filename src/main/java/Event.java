public class Event extends Task{
    String timeFrame;

    public Event(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public Event(String description, String timeFrame, boolean isDone) {
        super(description);
        this.timeFrame = timeFrame;
        this.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " ~ " + this.timeFrame;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + this.description + " (at:" + this.timeFrame + ")";
    }
}
