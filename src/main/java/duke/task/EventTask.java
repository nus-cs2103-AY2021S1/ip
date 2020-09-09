package duke.task;

public class EventTask extends Task {


  private final String at;

  public EventTask(String desc, String at) {
    super(desc);
    this.at = at;
  }

  public String getAt() {
    return at;
  }


  @Override
  public String toString() {
    return String.format("%d | %s | %s | %s | %s", getId(), getIsDone() ? "DONE" : "NOT DONE", "EVENT", getDesc(), at);
  }
}
