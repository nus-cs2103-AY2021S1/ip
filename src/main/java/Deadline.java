import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, dateTimeInputFormatter);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);  //  use default formatter that leaves no whitespace to trim
    }

    @Override
    public String[] serialize() {
        String[] output = new String[4];
        output[0] = this.isDone
                ? "1"
                : "0";
        output[1] = "Deadline";
        output[2] = description;
        output[3] = by.toString();

        return output;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(dateTimeOutputFormatter) + ")";
    }
}
