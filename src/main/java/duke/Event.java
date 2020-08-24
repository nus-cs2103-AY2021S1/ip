package duke;

public class Event extends Task {

    protected DukeDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = new DukeDate(at);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String fileFormat() {
        return "E , " + super.fileFormat() + at.getStringDate();
    }
}