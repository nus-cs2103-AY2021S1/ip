package duke.tasks;

/**
 * Event is a type of Task
 */
public class Event extends Task {

    private String timePeriod;

    /** Construct an event
     * @param name Description of the event.
     * @param timePeriod The period of the event.
     */
    public Event(String name, String timePeriod){
        super(name);
        this.timePeriod = timePeriod;
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String getTime(){
        return timePeriod;
    }

    /**
     * The toString method of Event.
     * @return The required String format of an event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod + ")";
    }

}
