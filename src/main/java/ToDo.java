public class ToDo extends Task {
  public ToDo(String description) {
    super(description);
  }

  @Override
  public String getData() {
    return "t/" + super.getData();
  }
  
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
