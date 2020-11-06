import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Deadline class is a type of listing that contains a String as the detail of the listing, a
 * boolean called isDone and a LocalDate object called deadLine.
 */

public class Deadline extends Listing {

    private LocalDate deadLine;

    /**
     * Creates a new Deadline object by passing s as the Listing message and parsing deadline to
     * LocalDate.
     *
     * @param s        detail of the listing
     * @param deadLine in the format YYYY-MM-DD
     * @return A DeadLine object
     */
    public Deadline(String s, String deadLine) {
        super(s);
        this.deadLine = LocalDate.parse(deadLine);
    }

    /**
     * Creates a new Deadline object using an extra string parameter doneness. Used when creating *
     * Event objects are loading from storage.
     *
     * @param doneness can be 0 or 1 and which gets passed to setDoneness that converts \ it to a
     *                 boolean
     * @param s        detail of the listing
     * @param doneness in the format YYYY-MM-DD
     * @return A DeadLine object
     */
    public Deadline(String doneness, String s, String time, String tagList) {
        super(s);
        setDoneness(doneness);
        this.deadLine = LocalDate.parse(time);
        this.tags = new ArrayList<>(Arrays.asList(tagList));
    }

    public Deadline(String doneness, String s, String time) {
        super(s);
        setDoneness(doneness);
        this.deadLine = LocalDate.parse(time);
    }

    /**
     * Returns a string array of size 4 containing a code, doneness, detail and time to be saved by
     * Storage.java. The code will always be "D" for "Deadline" and doneness wil be 1 or 0
     * corresponding to the object boolean value.
     *
     * @return A size 4 String array consisting of the details of the object
     */
    public String[] toArray() {
        String[] details = new String[5];
        details[0] = "D";
        details[1] = this.isDone ? "1" : "0";
        details[2] = this.title;
        details[3] = this.deadLine.toString();
        details[4] = this.tags.toString().substring(1, this.tags.toString().length() - 1);
        return details;
    }

    /**
     * @return this object in string format
     */
    @Override
    public String toString() {
        return this.tags.isEmpty() ? "[D]" + super.doneness() + this.title + " (by:"
                + deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                : "[D]" + super.doneness() + this.title + " (by:"
                + deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" + "\n        tags:" + this.tags;
    }

}
