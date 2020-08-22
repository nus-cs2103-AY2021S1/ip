import java.time.LocalDate;

public class Deadline extends Task {

    protected String by;
    int code = 1;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, LocalDate date) {
        super(description);
        this.by = by;
        this.date = date;
    }

    public static void invalidInput() {
        invalidInput("OOPS!!! The format of the Deadline is wrong.");
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

}