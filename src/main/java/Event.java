//package duke;
/**
 * Event <- Task
 */
public class Event extends Task {
    String description;
    String time;
    boolean isDone;

    /**
     * Init Event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Status in format [E][x] return book (at: 6 June)
     */
    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + time + ")";
    }


    /**
     * type of Task -> E for Event
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * description to write to data file
     */
    @Override
    public String getDescription() {
        return super.getDescription() + "|" + this.time;
    }
}
