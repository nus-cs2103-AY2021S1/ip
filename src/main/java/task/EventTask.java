package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
  LocalDateTime time;

  public EventTask(String name, LocalDateTime time) {
    super(name);
    this.time = time;
  }

  public EventTask(String name, int hasCompleted, LocalDateTime time) {
    super(name, hasCompleted);
    this.time = time;
  }

  public LocalDateTime getTime() {
    return time;
  }

  @Override
  public String toString() {
    return "[E]"
        + super.toString()
        + " (at: "
        + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
        + ")";
  }
}
