import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDate time;

    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public String getDate(){
        return time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }
}
