import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected String by;
  protected LocalDate date;
  protected LocalTime time;
  protected DateTimeFormatter dateParser =
          DateTimeFormatter.ofPattern("dd/MM/yy");
  protected DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("HH:mm");

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
    String s[] = by.split(" ");
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
      return "[D]" + super.toString() + " (by: " + dateParser.format(date) + ")";
    } else {
      return "[D]" + super.toString() + " (by: " + dateParser.format(date) + " " + timeParser.format(time) + ")";
    }
  }
}
