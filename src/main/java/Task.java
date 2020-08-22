public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return isDone
        ? "✓"
        : "✘";
  }
  
  public boolean getStatus() {
    return isDone;
  }

  public void setDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + this.description;
  }
}
