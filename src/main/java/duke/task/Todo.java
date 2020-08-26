package duke.task;

public class Todo extends Task {

  public Todo(String description) {
    super(description);
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }
}
