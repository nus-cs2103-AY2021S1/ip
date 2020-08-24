public class Event extends Task {

    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + time + ")";
    }

    public String encode() {
        return String.format("E|%s|%s|%s", super.completed ? "Y" : "N", this.time, super.description);
    }

    public static Event decode(String code) throws DukeException {
        if (code.charAt(0) == 'E') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("Your data is corrupt.");
            }
            Event newEvent = new Event(content[3], content[2]);
            if (content[1].equals("Y")) {
                newEvent.setCompleted();
            }
            return newEvent;
        } else {
            throw new DukeException("Unable to decode event.");
        }
    }
}
