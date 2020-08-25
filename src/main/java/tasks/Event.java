package tasks;

public class Event extends Task {
    protected String time;

    /**
     * Returns an Event task.
     *
     * @param description description of the event.
     * @param time time of the event.
     * @see Task
     */
    public Event(String description, String time){
        super(description);
        this.time = time;
    }

    /**
     * Returns the time of the event.
     *
     * @return String time of event.
     */
    public String getTime(){
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
