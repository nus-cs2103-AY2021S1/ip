public class Event extends Task {
    private String timeRange;

    Event(String name, String timeRange) {
        super(name, "E");
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }

    @Override
    public String getAbbreviatedString() {
        int isDoneRep = this.done ? 1 : 0;
        return String.format("%s | %d | %s | %s", this.type, isDoneRep, this.name, this.timeRange);
    }
}