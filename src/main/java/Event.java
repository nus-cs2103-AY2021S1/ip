public class Event extends Task {
  private String date;

  public Event(String name, String date) {
    super(name, "[E]");
    this.date = date;
  }

  @Override
  public String toString() {
    return String.format("%s (at: %s)", super.toString(), this.date);
  }
}
