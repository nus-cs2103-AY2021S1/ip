public class Event extends Task {
    String datetime;

    public Event(String content, String datetime, Boolean isComplete) throws DukeException {
        super(content, isComplete);
        if (datetime.replace(" ", "").equals("")) {
            throw new DukeException("Event datetime cannot be empty.");
        }
        this.datetime = datetime;
    }

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

    @Override
    public String toSaveString() {
        return String.format("T/%s/%s", super.toSaveString(), datetime);
    }
}
