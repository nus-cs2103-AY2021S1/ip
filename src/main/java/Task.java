public abstract class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
  }

  public int getStatusCode() {
    return (isDone ? 1 : 0);
  }

  public String getTaskName() {
    return description;
  }

  public void setDone() {
    this.isDone = true;
  }

  public String toString() {
    return "[" + getStatusIcon() + "] " + getTaskName();
  }

  public abstract String toFile();
}
