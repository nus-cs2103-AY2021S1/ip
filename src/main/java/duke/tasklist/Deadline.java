package duke.tasklist;

import java.util.ArrayList;

/**
 * Deadline class is a subclass of Task.
 * Deadline stores each Deadline's description and deadline.
 * @author Maguire Ong
 */

@SuppressWarnings("CheckStyle")
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, ArrayList<String> tags) {
        super(description, tags);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
