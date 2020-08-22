import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  private LocalDate date;

  public Deadline(String name, LocalDate date) {
    super(name, "[E]");
    this.date = date;
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  }
}
