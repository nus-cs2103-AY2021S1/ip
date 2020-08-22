public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }
  
  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  public String getStatusIcon() {
    return isDone
        ? "✓"
        : "✘";
  }
  
  public boolean getStatus() {
    return this.isDone;
  }
  
  public String getData() {
    return (this.isDone ? 1 : 0) + "/" + this.description;
  }

  public void setDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + this.description;
  }
}
