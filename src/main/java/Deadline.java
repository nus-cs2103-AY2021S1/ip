public class Deadline extends Task {
  private String date;

  public Deadline(String name, String date) {
    super(name, "[E]");
    this.date = date;
  }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), this.date);
  }
}
