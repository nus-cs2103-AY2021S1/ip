public class Task {
  private final static String DONE = "[✓]";
  private final static String NOT_DONE = "[✗]";

  private String name;
  private boolean isDone;

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  private String getStatus() {
    return this.isDone ? Task.DONE : Task.NOT_DONE;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.getStatus(), this.name);
  }
}