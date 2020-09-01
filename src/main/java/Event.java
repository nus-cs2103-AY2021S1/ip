/**
 * The Event class is a type of listing that contains a String as the detail of the listing, a
 * boolean called isDone and a String called deadLine.
 */

public class Event extends Listing {

  String deadLine;

  /**
   * Creates a new Event object by passing s as the Listing message and deadline as the deadline.
   * Note that deadline is not in a strict localDate format.
   *
   * @param s        detail of the listing
   * @param deadline deadline of the listing in any form of a string
   * @return A Event object
   */
  public Event(String s, String deadLine) {
    super(s);
    this.deadLine = deadLine;
  }

  /**
   * Creates a new Event object using an extra string parameter doneness. Used when creating Event
   * objects are loading from storage.
   *
   * @param doneness can be 0 or 1 and which gets passed to checkDoneness that converts \ it to a
   *                 boolean
   * @param s        detail of the listing
   * @param deadline in the format YYYY-MM-DD
   * @return A Event object
   */
  public Event(String doneness, String s, String time) {
    super(s);
    setDoneness(doneness);
    this.deadLine = time;
  }

  /**
   * Returns a string array of size 4 containing a code, doneness, detail and time to be saved by
   * Storage.java. The code will always be "D" for "Deadline" and doneness wil be 1 or 0
   * corresponding to the object boolean value.
   *
   * @return A size 4 String array consisting of the details of this object
   */
  public String[] toArray() {
    String[] details = new String[4];
    details[0] = "E";
    if (this.isDone) {
      details[1] = "1";
    } else {
      details[1] = "0";
    }
    details[2] = this.title;
    details[3] = this.deadLine;
    return details;
  }

  /**
   * @return This object in string format
   */
  @Override
  public String toString() {
    return "[E]" + super.doneness() + " " + this.title + "(at:" + this.deadLine + ")";
  }
}
