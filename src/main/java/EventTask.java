public class EventTask extends Task {
    private final String timePeriod;

    public EventTask(String description, String timePeriod) {
        super(description);
        this.timePeriod = timePeriod;
    }

    private EventTask(String description, boolean isDone, String timePeriod) {
        super(description, isDone);
        this.timePeriod = timePeriod;
    }

    @Override
    public EventTask markAsDone() {
        return new EventTask(description, true, timePeriod);
    }

    @Override
    public String printData() {
        return "E|" + super.printData() + "|" + timePeriod;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod + ")";
    }
}
