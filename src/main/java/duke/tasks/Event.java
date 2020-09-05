package duke.tasks;

import duke.Parser;

/**
 * Event is a subclass of task, with a time at which the task occurs
 */
public class Event extends Task {

    private final String time;

    /**
     * Constructor for event class
     * @param description description of the task
     * @param time time at which the event occurs
     * @param isDone indicates whether the task has been completed or not
     */
    public Event (String description, String time, boolean isDone) {
        super (description, "Event", isDone);
        this.time = time;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        Parser parser = new Parser();
        if (isDone) {
            return String.format ("[E][DONE] %s (at: %s)", this.description, parser.convertDate(time));
        } else {
            return String.format ("[E][NOT DONE] %s (at: %s)", this.description, parser.convertDate(time));
        }
    }
}
