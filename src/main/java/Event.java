public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String encode() {
        return String.format("E|%s|%s|%s", super.isDone ? "Y" : "N", this.at, super.description);
    }

    public static Event decode(String code) throws DukeException {
        if (code.charAt(0) == 'E') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("data string is not equal to 4");
            }
            Event newEvent = new Event(content[3], content[2]);
            if (content[1].equals("Y")) {
                newEvent.markAsDone();
            }
            return newEvent;
        } else {
            throw new DukeException("Unable to decode event");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
