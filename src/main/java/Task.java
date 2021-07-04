/** Task class Represents a task along with the description and the status of the task */
public class Task {
  protected String description;
  protected boolean isDone;

  /**
   * Constructor that takes in a description of the task
   *
   * @param description
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Return type of task, to be overwritten by its subclasses
   *
   * @return ""
   */
  public String getType() {
    return "";
  }

  /** Marks task as done */
  public void markDone() {
    this.isDone = true;
  }

  /**
   * Returns description of task
   *
   * @return description of task
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Return status as a string representation
   *
   * @return status
   */
  public String getStatusIcon() {
    return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or X symbols
  }

  @Override
  public String toString() {
    return getStatusIcon() + " " + description;
  }
}
