public class Event extends Task {

    private String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public static Event parse(String[] split) {
        Event event = new Event(split[2], split[3]);
        if (split[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    public String serialize() {
        return "E|" + super.serialize() + "|" + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
