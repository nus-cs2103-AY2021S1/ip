package duke.task;

public class Event extends Task {

    protected LocalDate date = null;
    protected String at = null;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        tag = "E";
        String[] bySplit = at.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.at = bySplit[1];
        }
    }

    public Event(String description) {
        super(description);
        tag = "E";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return super.toPrint() + "|" + date + "|" + at;
    }

    @Override
    public String toString() {
        if (at == null) {
            return "[E]" + super.toString();
        } else {
            String now = "AM";
            LocalTime localTime = LocalTime.parse(at, DateTimeFormatter.ofPattern("HHmm"));
            int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
            int minute = localTime.get(ChronoField.MINUTE_OF_HOUR);
            if (hour > 12) {
                now = "PM";
                hour -= 12;
            }
            return "[E]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", "+ hour + ":" + minute + now + ")" ;
        }
    }
}