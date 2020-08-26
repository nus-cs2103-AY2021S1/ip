package duke.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Representation as a point of time as a <code>Date</code>, and if available, <code>Time</code>.
 */
public class DateTime extends TimePoint{

    /**
     * Date of point in time represented by <code>DateTime</code>.
     */
    private LocalDate date;

    /**
     * Time of point in time represented by <code>DateTime</code>. May not be initialised depending on given input.
     */
    private LocalTime time;

    private boolean haveTime;

    /**
     * Creates a new <code>DateTime</code> representing a date.
     *
     * @param date <code>Date</code> to be represented by the <code>DateTime</code>.
     */
    public DateTime(LocalDate date) {
        this.date = date;
        this.haveTime = false;
    }

    /**
     * Creates a new <code>DateTime</code> representing a date and a time.
     *
     * @param date <code>Date</code> to be represented.
     * @param time <code>Time</code> to be represented.
     */
    public DateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
        this.haveTime = true;
    }

    /**
     * Returns string representation of this <code>DateTime</code> for display in dd MMMM yyyy h:mma.
     * See <code>java.time.format.DateTimeFormatter</code> for meaning of the abbreviated time format pattern.
     *
     * @return String representation of date and time.
     */
    @Override
    public String toString() {

        // String format for date
        String text = this.date.format(DateTimeFormatter
                .ofPattern("dd MMMM yyyy", new Locale("en")));

        // If time data available, add to text string
        if (haveTime) {
            text += " " + this.time.format(DateTimeFormatter
                    .ofPattern("h:mma", new Locale("en")));
        }

        return text;
    }

    /**
     * Returns formatted string for saving in to save file.
     * Note that this string is different from <code>toString()</code>.
     *
     * @return Formatted string.
     */
    @Override
    public String toSaveString() {

        // Save String format for data
        String saveString = this.date.format(DateTimeFormatter
                .ofPattern("dd/MM/yyyy", new Locale("en")));

        // If time data available, add to save string
        if (haveTime) {
            saveString += " " + this.time.format(DateTimeFormatter
                    .ofPattern("HH:mm", new Locale("en")));
        }

        return saveString;
    }

}
