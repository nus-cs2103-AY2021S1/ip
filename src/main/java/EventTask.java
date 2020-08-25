public class EventTask extends Task {
    String time;
    public EventTask(
            Boolean isDone,
            String name,
            String time
    ) {
        super(isDone, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]|%s|%s", super.toString(), time);
    }
}
