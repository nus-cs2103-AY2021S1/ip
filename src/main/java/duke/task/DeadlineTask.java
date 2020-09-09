package duke.task;

public class DeadlineTask extends Task {


  private final String by;

  public DeadlineTask(String desc, String by) {
    super(desc);
    this.by = by;
  }

  public String getBy() {
    return by;
  }


  @Override
  public String toString() {
    return String
        .format("%d | %s | %s | %s | %s", getId(), getIsDone() ? "DONE" : "NOT DONE", "DEADLINE",
            getDesc(), by);
  }
}
