package duke.tasklist;

import java.util.ArrayList;

/**
 * Event class is a subclass of Task.
 * Event stores each Event's description and event time.
 * @author Maguire Ong
 */

public class Event extends Task {
    protected String by;

    public Event(String description, String by, ArrayList<String> tags) {
        super(description, tags);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
