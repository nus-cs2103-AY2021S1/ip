package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Event class define rules for Event object. Event is a subclass of Task class.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Event extends Task {

  public LocalDateTime at;
  public LocalDateTime end;

  /**
   * Class constructor. Initialise event class with description, isDone and time.
   *
   * @param description description of the event.
   * @param at date and start of the event.
   * @param end data and time end of the event.
   */
  public Event(String description, LocalDateTime at, LocalDateTime end) {
    super(description);
    this.at = at;
    this.end = end;
  }

  /**
   * Check if two object are equal. if equal true, else false.
   *
   * @param o object to be compare.
   * @return true is same, false if different
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Event event = (Event) o;
    return Objects.equals(at, event.at) && Objects.equals(end, event.end);
  }

  /**
   * Summary to the event object.
   *
   * @return string to the event object.
   */
  @Override
  public String toString() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
    return String.format(
        "[E]%s (at: %s till %s)", super.toString(), at.format(dtf), end.format(dtf));
  }
}
