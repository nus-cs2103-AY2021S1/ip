public class Event extends Task {
    String datetime;

    public Event(String content, String datetime) throws DukeException{
        super(content);
        if (datetime.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}
