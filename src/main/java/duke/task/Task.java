package duke.task;

/** Represents a generic task. */
public abstract class Task {
  protected String description;
  protected boolean isDone;

  /**
   * Constructor of a generic task with description and task status indicating whether it is
   * completed.
   *
   * @param description Description of the task.
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * A method to display the tick or cross symbol to indicate 'done' or 'not done' respectively.
   *
   * @return UTF-8 Tick or X symbol.
   */
  public String getStatusIcon() {
    return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
  }

  /**
   * A method to display/print '1' or '0' to indicate 'done' or 'not done' respectively in the save
   * file.
   *
   * @return Integer '1' or '0'.
   */
  public int getStatusCode() {
    return (isDone ? 1 : 0);
  }

  /**
   * A method to display/print task name.
   *
   * @return Task Name.
   */
  public String getTaskName() {
    return description;
  }

  /** A method to mark a task as 'done'. */
  public void setDone() {
    this.isDone = true;
  }

  /**
   * A method to display the Task attributes in String format.
   *
   * @return Task attributes in a string.
   */
  public String toString() {
    return "[" + getStatusIcon() + "] " + getTaskName();
  }

  /**
   * An abstract method to allow different task types to output into the save file.
   *
   * @return Task attributes in a string.
   */
  public abstract String toFile();
}
