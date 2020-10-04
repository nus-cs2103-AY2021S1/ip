package duke.task;

public class Task {

  public static int numTasks = 0;
  private final int id;
  private final String desc;
  private boolean isDone = false;

  public Task(String desc) {
    this.desc = desc;
    this.id = ++numTasks;
  }

  public int getId() {
    return id;
  }

  public String getDesc() {
    return desc;
  }


  public boolean getIsDone() {
    return isDone;
  }

  public void setIsDone(boolean isDone) {
    this.isDone = isDone;
  }

  public void markDone() {
    setIsDone(true);
  }

  @Override
  public String toString() {
    return String.format("%d | %s | %s | %s", id, isDone ? "DONE" : "NOT DONE", "TODO", desc);
  }
}
