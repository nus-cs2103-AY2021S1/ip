package duke.task;

public class Deadline extends Task {

    protected LocalDate date = null;
    protected String by = null;

    public Deadline(String description, String by) {
        super(description);
        tag = "D";
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.by = bySplit[1];
        }
    }

    public Deadline(String description) {
        super(description);
        tag = "D";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return super.toPrint() + "|" + date + "|" + by;
    }

    @Override
    public String toString() {
        if (by == null) {
            return "[D]" + super.toString();
        } else {
            String now = "AM";
            LocalTime localTime = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            if (hour > 12) {
                now = "PM";
                hour -= 12;
            }
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", "+ hour + ":" + minute + now + ")" ;
        }
    }
}