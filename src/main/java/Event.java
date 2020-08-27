public class Event extends Task {
    private TaskDate start;
    private TaskDate end;

    Event(String name, String timeRange) {
        super(name, "E");
        this.start = DateParser.getRange(timeRange, true);
        this.end = DateParser.getRange(timeRange, false);
    }

    Event(String name, TaskDate start, TaskDate end) {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(), this.start, this.end.getTime());
    }

    @Override
    public String getAbbreviatedString() {
        int isDoneRep = this.done ? 1 : 0;
        return String.format("%s | %d | %s | %s - %s", this.type, isDoneRep, this.name,
                                    this.start.toString(), this.end.getTime());
    }
}