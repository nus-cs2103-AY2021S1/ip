package duke.tasks;

/**
 * Event is a type of Task
 */
public class Event extends Task {

    private String timePeriod;

    /**
     * Constructs an event
     *
     * @param name       Description of the event.
     * @param timePeriod The period of the event.
     */
    public Event(String name, String timePeriod) {
        super(name);
        this.timePeriod = timePeriod;
    }

    /**
     * Gets the type of Event.
     * @return "E" which represents event
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Gets the period of time of the event.
     * @return The period of time of the event
     */
    @Override
    public String getTime() {
        return timePeriod;
    }

    /**
     * Convert the information of an event into array.
     *
     * @return A array of String.
     */
    public String[] toArray(){
        String status;
        if(this.isDone){
            status = "1";
        }else{
            status = "0";
        }
        String[] result = new String[]{"E", status, this.name, timePeriod};
        return result;
    }

    /**
     * The toString method of Event.
     *
     * @return The required String format of an event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timePeriod + ")";
    }

}
