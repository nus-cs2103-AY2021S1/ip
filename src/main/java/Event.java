public class Event extends Task {
    private TaskDate start;
    private TaskDate end;

    Event(String name, boolean isDone, TaskDate start, TaskDate end) {
        super(name, isDone,"E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(), this.start, this.end.getTime());
    }

    @Override
    public String getAbbreviatedString() {
        int isDoneRep = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s - %s", this.type, isDoneRep, this.name,
                                    this.start.toString(), this.end.getTime());
    }
}