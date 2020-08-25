import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a specific type of Task that has a deadline as additional information.
 *  Following the convention for String input is crucial for successful instantiation.
 */
public class Deadline extends Task{
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        String[] timeComponent = by.split(" ");
        this.date = LocalDate.parse(format(timeComponent[0].trim()));
        String hour = timeComponent[1].substring(0,2);
        String minute = timeComponent[1].substring(2);
        this.time = LocalTime.of(Integer.parseInt(hour),
                Integer.parseInt(minute));
    }

    /**
     * Converts the user input into a format that can be used for instantiating
     * a java.time.LocalDate object.
     * @param input user input
     * @return String representation of user input in certain format
     */
    private String format(String input) {
        String[] component = input.split("/");
        if (component[0].length() == 1) {
            component[0] = "0" + component[0];
        }
        return component[2] + "-" + component[1] +
                "-" + component[0];
    }

    /**
     * Returns a String representation of the deadline
     * @return String representation of the Data and Time attached to this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + " " + time + ")";
    }

    @Override
    public String getDate() {
        return this.by.split(" ")[0];
    }

    /**
     * Returns the data being written on hard disk.
     * @return String representation of the text for storing in txt.file
     */
    @Override
    public String convertTxt() {
        return "D | " + (this.status ? "1" : "0") + " | " + name + " | " + by;
    }
}
