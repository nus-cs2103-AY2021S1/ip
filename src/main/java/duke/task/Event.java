package duke.task;


/**
 * Represents an event task with description and at-time.
 * Inherits from Task.
 */
public class Event extends Task {

    /**
     * Initializes with a description and the time of event.
     *
     * @param desc Description.
     * @param at Time of the event.
     */
    public Event(String desc, String at) {
        super(desc);
        this.type = TaskType.EVENT;
        this.time = at;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String printSaveFormat() {
        return "event " + super.printSaveFormat() + " /at " + time;
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    /**
     * Subroutine for deep-copying an event
     *
     * @param t Event task to be copied.
     * @return Deep copy of the event task given.
     */
    public static Event deepCopyEvent(Task t) {
        Event eventCopy = new Event(t.description, t.time);
        eventCopy.status = t.status;
        return eventCopy;
    }
}
