import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate time;

    Deadline(String description, String time) {
        super(description);
        this.time = Parser.stringToDate(time);
    }

    @Override
    public String toString() {
        String timeStr = Parser.dateToString(this.time);
        return String.format("[D]%s(by: %s)", super.toString(), timeStr);
    }

    @Override
    public String toSaveString() {
        return String.format("%s || deadline || %s || %s", super.toSaveString(), this.description, this.time);
    }
}
