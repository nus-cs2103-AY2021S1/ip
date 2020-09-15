package duke;

/**
 * Event class encapsulates Event object which is a subtype of Task.
 */
public class Event extends Task{
    private String duration;
    private static final String EVENT_SYMBOL = "[E]";

    /**
     * Constructor for Event class.
     * @param name String description or name of event
     * @param isCompleted boolean indicating completion of task. True means task
     *                    has been completed. False means vice versa.
     * @param duration String duration of event
     */
    public Event (String name,boolean isCompleted, String duration) {
        super(name,isCompleted);
        this.duration = duration;
    }
    private String getDuration() {
        return this.duration;
    }

    /**
     * 
     * @return the symbol of the Event class as a String
     */
    public String getTaskSymbol() {
        return EVENT_SYMBOL;
    }

    /**
     * Formats the Event object to be stored in the Database.
     * @return the storage of the event as a String
     */
    public String storeFormat() {
        return String.format("%s %s %s %s",this.getTaskSymbol(),this.isDone(),this.description,this.duration);
    }
    
    public String toString () {
        return String.format("%s (at: %s)",this.description,this.duration);
    }
}
