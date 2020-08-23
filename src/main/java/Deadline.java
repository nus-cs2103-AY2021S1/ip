import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  LocalDateTime by;

  public Deadline(String description, LocalDateTime by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
    return String.format("[D]%s (by: %s)", super.toString(), by.format(dtf));
  }
}
