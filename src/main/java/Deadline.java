public class Deadline extends Task {

    private final DateAndTime datetime;

    public Deadline(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.datetime = new DateAndTime(date, time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetime + ")";
    }
}
