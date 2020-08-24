package duke.task;

/** Different possible TaskType */
public enum TaskType {
  TODO("[T]"),
  EVENT("[E]"),
  DEADLINE("[D]");

  private String name;

  TaskType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
