package Duke.Tasks;

/**
 * The Event is a subclass of Task and it is used to describe tasks that has to be completed by a specific day and time
 */
public class Event extends Task {
    private String dateTime;
    private String end;

    /**
     * Initializes the Event class
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param dateTime assigns this.dayTime to dayTime value
     */
    public Event(String name, String dateTime, String end) {
        super(name);
        this.dateTime = dateTime;
        this.end = end;
    }

    /**
     * Assigns the nam, done and day values
     *
     * @param name argument in super class constructor
     * @param done argument in super class constructor
     */
    public Event(String name, boolean done, String dateTime, String end) {
        super(name, done);
        this.dateTime = dateTime;
        this.end = end;
    }

    /**
     * Overrides the toString methods
     *
     * @return the specific representation for Event class as mentioned with [E] indicating that it is a Event class
     *      * and also mentions the Event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateTime +  "-" + this.end + ")";
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for Event
     *
     * @return the string representation
     */
    public String inputListFormat(){
        return "E" + super.inputListFormat() + " | " + this.dateTime + "-" + this.end; //format of Tasks to appear in file in Storage
    }
}