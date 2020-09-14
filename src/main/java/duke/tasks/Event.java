package duke.tasks;

/**
 * The Event is a subclass of Task and it is used to describe tasks that has to be completed by a specific day and time
 */
public class Event extends Task {
    private String startDateAndOrTime; //the start date and/or time of the event
    private String endDateAndOrTime; //the end date and/or time of the event

    /**
     * Assigns the name, done and day variables with values and used to initialize Event task
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param start assigns this.dayTime to dayTime value
     */
    public Event(String name, String start, String end) {
        super(name);
        this.startDateAndOrTime = start;
        this.endDateAndOrTime = end;
    }

    /**
     * Assigns the name, done and day variables with values and used to initialize Event task
     *
     * @param name argument in super class constructor
     * @param done argument in super class constructor
     * @param startDateAndOrTime argument used to assign this.startDateAndOrTime with value
     * @param endDateAndOrTime  argument assigns this.endDateAndOrTime with value
     */
    public Event(String name, boolean done, String startDateAndOrTime, String endDateAndOrTime) {
        super(name, done);
        this.startDateAndOrTime = startDateAndOrTime;
        this.endDateAndOrTime = endDateAndOrTime;
    }

    /**
     * Overrides the toString methods
     *
     * @return the specific representation for Event class as mentioned with [E] indicating that it is a Event class
     *      * and also mentions the Event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.startDateAndOrTime + "-" + this.endDateAndOrTime + ")";
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for Event
     *
     * @return the string representation
     */
    public String inputListFormat() {
        return "E" + super.inputListFormat() + " | " + this.startDateAndOrTime + "-" + this.endDateAndOrTime;
        //format of Tasks to appear in file in Storage
    }
}
