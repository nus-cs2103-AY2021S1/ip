package duke.task;

public class Event extends Task {
    private String atTime;

    /**
     * Contructs an event object.
     * @param eventTask Name of event.
     * @param atTime Time at which event happens.
     */
    public Event(String eventTask, String atTime) {
        super(eventTask);
        this.atTime = atTime;
    }

    /**
     * Returns the event in array form.
     * @return String array.
     */
    @Override
    public String[] taskToArray() {
        String done;
        if(this.isCompleted()) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"E", done, this.getTaskName(), atTime};
        return str;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atTime + ")";
    }
}
