public class Event extends Task {
    private TaskDate start;
    private TaskDate end;

    Event(String name, String timeRange) {
        super(name);
        this.start = DateParser.getRange(timeRange, true);
        this.end = DateParser.getRange(timeRange, false);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(), this.start, this.end.getTime());
    }
}
