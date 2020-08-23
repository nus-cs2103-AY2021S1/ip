import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  LocalDateTime at;
  LocalDateTime end;

  public Event(String description, LocalDateTime at, LocalDateTime end) {
    super(description);
    this.at = at;
    this.end = end;
  }

  @Override
  public String toString() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
    return String.format(
        "[E]%s (at: %s till %s)", super.toString(), at.format(dtf), end.format(dtf));
  }
}
