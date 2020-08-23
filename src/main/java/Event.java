import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate time;

    Event(String description, String time) {
        super(description);
        this.time = Parser.stringToDate(time);
    }

    @Override
    public String toString() {
        String timeStr = Parser.dateToString(this.time);
        return String.format("[E]%s(at: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || event || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
