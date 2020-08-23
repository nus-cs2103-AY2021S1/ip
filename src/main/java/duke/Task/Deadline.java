package duke.Task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    /**
     * Constructs a Deadline object to represent a Deadline Task
     * @param task description of task
     * @param dateTime LocalDateTime object that represents the deadline
     */
    public Deadline(String task, LocalDateTime dateTime) {
        super(task);
        this.dateTime = dateTime;
    }

    /**
     * Returns a String in the format of "dd MM yyyy hh mm"
     * @return String that represents the date and time of the Deadline Task
     */
    public String getDateTime() {
        int dd = this.dateTime.getDayOfMonth();
        int mm = this.dateTime.getMonthValue();
        int yyyy = this.dateTime.getYear();
        int hour = this.dateTime.getHour();
        int min = this.dateTime.getMinute();
        return dd + " " + mm + " " + yyyy + " " + hour + " " + min;
    }

    public String toString() {
        String MMM = this.dateTime.getMonth().toString().substring(0, 3);
        String DDD = this.dateTime.getDayOfWeek().toString().substring(0, 3);
        int dd = this.dateTime.getDayOfMonth();
        int yyyy = this.dateTime.getYear();
        String HHHH = this.dateTime.toLocalTime().toString();
        return "[D]" + super.toString() + "(at: " + dd + " " + MMM + " " + yyyy + ", " + DDD + " @ " + HHHH + ")";
    }
}