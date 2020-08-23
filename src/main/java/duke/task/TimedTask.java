package duke.task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Encapsulates a task with possible date and time assigned to it.
 */

public class TimedTask extends Task {

    protected String dateTime;
    protected LocalDate date;
    protected Date time;
    protected String time2;
    protected boolean hasDate;
    protected boolean hasTime;

    protected String firstLetter;
    protected String connecting;

    /**
     * Creates a new Timed Task with the description and the date and time, if any.
     * @param description Details of the task.
     * @param dateTime Date and time of the task.
     */
    public TimedTask(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime.trim();
        this.hasDate = false;
        try {
            String[] arr = this.dateTime.split(" ", 2);
            this.date = LocalDate.parse(arr[0]);
            this.hasDate = true;
            if (!this.dateTime.contains(" ")) {
                this.hasTime = false;
                time2 = "";
            } else {
                try {
                    this.time = new SimpleDateFormat("hh:mm").parse(arr[1]);
                    this.hasTime = true;
                } catch (Exception e) {
                    this.time2 = " " + arr[1];
                    this.hasTime = false;
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Expresses the timed task as a String.
     * If there is date and/or time, it is reformatted.
     * @return A string representation of the timed task.
     */
    @Override
    public String toString() {
        if (hasDate) {
            if (hasTime) {
                SimpleDateFormat dfFormat = new SimpleDateFormat("h:mm a");
                return firstLetter + super.toString() + connecting + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + dfFormat.format(time) + ")";
            } else {
                return firstLetter + super.toString() + connecting + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))  + time2 + ")";
            }
        } else {
            return firstLetter + super.toString() + connecting + dateTime + ")";
        }
    }
}
