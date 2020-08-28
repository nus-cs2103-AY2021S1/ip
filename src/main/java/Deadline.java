import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Listing {

  private LocalDate deadLine;

  /**
   * Creates a new Deadline object by passing s as the Listing message and parsing deadline to
   * LocalDate.
   *
   * @param s        detail of the listing
   * @param deadline in the format YYYY-MM-DD
   */
  public Deadline(String s, String deadLine) {
    super(s);
    this.deadLine = LocalDate.parse(deadLine);
  }

  /**
   * Creates a new Deadline object using an extra string parameter doneness. Used when creating
   *    * Event objects are loading from storage
   *
   * @param doneness can be 0 or 1 and which gets passed to checkDoness that converts \ it to a
   *                 boolean
   * @param s        detail of the listing
   * @param deadline in the format YYYY-MM-DD
   */
  public Deadline(String doneness, String s, String time) {
    super(s);
    checkDoneness(doneness);
    this.deadLine = LocalDate.parse(time);
  }

  /**
   * Returns a string array of size 4 containing a code, doneness, detail and time to be saved by
   * Storage.java. The code will always be "D" for "Deadline" and doneness wil be 1 or 0
   * corresponding to the object boolean value
   */
  public String[] toArray() {
    String[] details = new String[4];
    details[0] = "D";
    if (this.isDone) {
      details[1] = "1";
    } else {
      details[1] = "0";
    }
    details[2] = this.title;
    details[3] = this.deadLine.toString();
    return details;
  }

  /**
   * Returns this object in string format.
   */
  @Override
  public String toString() {
    return "[D]" + super.doneness() + this.title + " (by:" +
        deadLine.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }

}
