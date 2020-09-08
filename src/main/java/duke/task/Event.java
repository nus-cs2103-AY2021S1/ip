package duke.task;

public class Event extends Task {
    private String atTime;

    /**
     * Constructs an event object.
     *
     * @param eventTask Name of event.
     * @param atTime Time at which event happens.
     */
    public Event(String eventTask, String atTime) {
        super(eventTask);
        this.atTime = atTime;
    }

    /**
     * Returns the event in array form.
     *
     * @return String array.
     */
    @Override
    public String[] taskToArray() {
        String done;
        if (this.isCompleted()) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str;
        if (!this.isTagged()) {
            str = new String[]{"E", done, this.getTaskName(), atTime};
        } else {
            str = new String[]{"E", done, this.getTaskName(), atTime, this.getTagName()};
        }
        return str;
    }

    @Override
    public String toString() {
        if (!this.isTagged()) {
            return "[E]" + super.toString() + " (at: " + atTime + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + atTime + ") " + this.getTagName();
        }
    }
}
