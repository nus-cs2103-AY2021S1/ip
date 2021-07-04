import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event class Represents an event task that contains a description along with the date and time.
 */
public class Event extends Task {

  protected String startTime;

  /**
   * Constructor that takes in event description and the date/time
   *
   * @param description event description
   * @param startTime date/time of event
   */
  public Event(String description, String startTime) {
    super(description);
    this.startTime = startTime;
  }

  /**
   * Returns the string representation of the date/time
   *
   * @return original string representation of date/time
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Returns a modified string representation of the date/time
   *
   * @return modified string representation of date/time
   */
  public String getTime() {
    try {
      Date d1 = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(startTime);
      return new SimpleDateFormat("HH:mm, dd MMM yyyy").format(d1);
    } catch (ParseException e) {
      System.out.println("Invalid format");
    }
    return "";
  }

  public void changeStartTime(String newStartTime) throws DukeException {
    try {
      new SimpleDateFormat("dd/MM/yyyy HHmm").parse(newStartTime);
      this.startTime = newStartTime;
    } catch (ParseException e) {
      throw new DukeException(
          "Invalid date format! Please ensure date in this format dd/MM/yyyy HHmm");
    }
  }

  /**
   * Returns task type
   *
   * @return "E"
   */
  public String getType() {
    return "E";
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + getTime() + ")";
  }
}
