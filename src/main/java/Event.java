public class Event extends TimedTask {

    public Event(String description, String at) {
        super(description,at);
        this.connecting = " (at: ";
        this.firstLetter = "[E]";
    }
}