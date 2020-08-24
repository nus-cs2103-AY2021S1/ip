import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
  protected String at;
  protected LocalDate date;
  protected LocalTime time;
  protected DateTimeFormatter dateParser = DateTimeFormatter.ofPattern("dd/MM/yy");
  protected DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("HH:mm");

  public Event(String description, String at) {
    super(description);
    this.at = at;
    String s[] = at.split(" ");
    if (s.length <= 1) {
      time = null;
    } else {
      time = LocalTime.parse(s[1], timeParser);
    }
    date = LocalDate.parse(s[0], dateParser);
  }

  @Override
  public String toString() {
    if (time == null) {
      return "[E]" + super.toString() + " (at: " + dateParser.format(date) + ")";
    } else {
      return "[E]"
          + super.toString()
          + " (at: "
          + dateParser.format(date)
          + " "
          + timeParser.format(time)
          + ")";
    }
  }

  @Override
  public String toFile() {
    return "E | " + getStatusCode() + " | " + description + " | " + at;
  }
}
