public class Event extends Task {
  String at;

  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  @Override
  public String getData() {
    return "e/" + super.getData() + "/" + this.at;
  }
  
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + at + ")";
  }
}
