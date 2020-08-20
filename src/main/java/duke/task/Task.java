package duke.task;

public class Task {
  private String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getDescription() {
    return description;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setIsDone(boolean isDone) {
    this.isDone = isDone;
  }
}
