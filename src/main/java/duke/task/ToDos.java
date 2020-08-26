package duke.task;

/**
 * Represents a specific task which is a to do.
 */
public class ToDos extends Task {

    protected LocalDate date = null;
    protected String by = null;

    public ToDos(String description, String by) {
        super(description);
        tag = "T";
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.by = bySplit[1];
        }
    }

    public ToDos(String description) {
        super(description);
        tag = "T";
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
            return "[T]" + super.toString();
        } else {
            String now = "AM";
            LocalTime localTime = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            if (hour > 12) {
                now = "PM";
                hour -= 12;
            }
            return "[T]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", "+ hour + ":" + minute + now + ")" ;
        }
    }
}