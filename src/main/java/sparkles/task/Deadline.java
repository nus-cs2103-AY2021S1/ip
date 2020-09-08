package sparkles.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a Deadline object.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;
    protected String by;


    /**
     * Create a Deadline object representing
     * a deadline task.
     *
     * @param description description of the deadline
     * @param by          Date and time it is due by
     */
    public Deadline(String description, String by) {
        super(description);
        parseDateAndTime(by);
        this.by = by;
        super.typeOfTask = "D";
    }

    private void parseDateAndTime(String by) {
        String[] arr = by.split(" ");
        date = LocalDate.parse(arr[0]);
        time = LocalTime.parse(arr[1]);
    }


    private String printDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateFormatter);
    }

    private String printTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return time.format(timeFormatter);
    }

    /**
     * Print the date and time of the deadline in "MMM dd yyy hh:mm a" format.
     *
     * @return String of date and time of deadline
     */
    public String printDateNTime() {
        return printDate() + " " + printTime();
    }

    /**
     * Package the deadline to a format used to store in the task.txt.
     * A file in the local disk to store tasks.
     *
     * @return String of Deadline's details in custom disk format
     */
    @Override
    public String convertToDiskFormat() {
        return "     D | " + super.convertToDiskFormat() + " | " + by;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Deadline d = (Deadline) obj;
            return this.by.equals(d.by);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDateNTime() + ")";
    }
}
