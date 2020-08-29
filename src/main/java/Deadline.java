import java.text.SimpleDateFormat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;

public class Deadline extends Task {
    private String date;
    private String time;

    Deadline(String description, String date, String time) {
        super(description);
        try {
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            this.date = new SimpleDateFormat("MMM d yyyy").format(d);

            LocalTime t = LocalTime.parse(time.substring(0, 2) + ":" + time.substring(2));
            this.time = DateTimeFormatter.ofPattern("h.mma").format(t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Deadline(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        String[] split = dateTime.split(",");
        this.date = split[0].trim();
        this.time = split[1].trim();
    }

    /**
     * Returns the deadline of the task.
     * Deadline is returned in the format: MMM d yyyy, h.mma.
     *
     * @return deadline of the task
     */
    public String getDeadline() {
        return date + ", " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
