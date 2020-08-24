package duke.task;

import java.time.LocalDate;
import java.util.Optional;

/** Task */
public class Task {
  private static final String DONE = "[x]";
  private static final String NOT_DONE = "[ ]";

  private final String name;
  private TaskType taskType;
  private boolean isDone;
  private Optional<LocalDate> optionalDate;

  public Task(String name, TaskType type) {
    this.name = name;
    this.isDone = false;
    this.taskType = type;
    this.optionalDate = Optional.empty();
  }

  public Task(String name, TaskType type, LocalDate optionalDate) {
    this.name = name;
    this.isDone = false;
    this.taskType = type;
    this.optionalDate = Optional.of(optionalDate);
  }

  /**
   * Returns a String that show the status of the task
   *
   * @return String that shows the status of the task
   */
  public String getStatus() {
    return this.isDone ? Task.DONE : Task.NOT_DONE;
  }

  /**
   * Returns an Optional object containing the date associated with the task
   *
   * @return an Optional object containing the date associated with the task
   */
  public Optional<LocalDate> getDate() {
    return this.optionalDate;
  }

  /**
   * Sets the task as done or not done
   *
   * @param isDone a boolean that determines if the task is done (true if done, false if not done)
   */
  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  @Override
  public String toString() {
    return String.format("%s%s %s", this.taskType, this.getStatus(), this.name);
  }
}
