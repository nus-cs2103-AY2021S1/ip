import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String summarize() {
        return String.format("E | %s | %s", super.summarize(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

