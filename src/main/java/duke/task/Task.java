package duke.task;

import java.time.LocalDate;
import java.util.Optional;

public class Task {
  private static final String DONE = "[x]";
  private static final String NOT_DONE = "[ ]";

  private final String name;
  private TaskType taskType;
  private boolean isDone;
  private Optional<LocalDate> date;

  public Task(String name, TaskType type) {
    this.name = name;
    this.isDone = false;
    this.taskType = type;
    this.date = Optional.empty();
  }

  public Task(String name, TaskType type, LocalDate date) {
    this.name = name;
    this.isDone = false;
    this.taskType = type;
    this.date = Optional.of(date);
  }

  public String getStatus() {
    return this.isDone ? Task.DONE : Task.NOT_DONE;
  }

  public Optional<LocalDate> getDate() {
    return this.date;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  @Override
  public String toString() {
    return String.format("%s%s %s", this.taskType, this.getStatus(), this.name);
  }
}
