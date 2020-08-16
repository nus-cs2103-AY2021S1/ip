public class Task {
  private static final String DONE = "[✓]";
  private static final String NOT_DONE = "[✗]";

  private String name;
  private String type;
  private boolean isDone;

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  public Task(String name, String type) {
    this.name = name;
    this.isDone = false;
    this.type = type;
  }

  private String getStatus() {
    return this.isDone ? Task.DONE : Task.NOT_DONE;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  @Override
  public String toString() {
    return String.format("%s%s %s", this.type, this.getStatus(), this.name);
  }
}
