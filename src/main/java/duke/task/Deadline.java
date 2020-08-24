package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Deadline Task with a specified date that that is the time of the Deadline */
public class Deadline extends Task {
  public Deadline(String name, LocalDate date) {
    super(name, TaskType.DEADLINE, date);
  }

  @Override
  public String toString() {
    return String.format(
        "%s (by: %s)",
        super.toString(), super.getDate().get().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  }
}
