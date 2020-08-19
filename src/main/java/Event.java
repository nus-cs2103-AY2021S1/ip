public class Event extends Task  {
    public String time;
    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public static Event create(String description) {
        String[] keywords = description.split(" /at ", 2);
        return new Event(keywords[0], keywords[1]);
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
