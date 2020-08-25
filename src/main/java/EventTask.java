public class EventTask extends Task {
    String period;

    public EventTask(String desc, String period) {
        super(desc);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()  + " (at: " + period + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("T @@ %d @@ %s @@ %s", isDone ? 1 : 0, desc, period);
    }
}
