package duke.task;

public class Task {
  public String description;
  public boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "\u2713" : "\u2718"); // return tick of X symbols
  }

  public void markAsDone() {
    this.isDone = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return isDone == task.isDone && description.equals(task.description);
  }

  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }
}
