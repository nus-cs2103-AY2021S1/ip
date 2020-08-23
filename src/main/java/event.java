/**
 * The event is a subclass of Task and it is used to describe tasks that has to be completed by a specific day and time
 */
public class event extends Task {
    private String dateTime;
    /**
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param dateTime assigns this.dayTime to dayTime value
     */
    public event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }
    /**
     *  Overrides the toString methods
     * @return the specific representation for event class as mentioned with [E] indicating that it is a event class
     *      * and also mentions the event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateTime + ")";
    }
    public String inputListFormat(){
        return "E" + super.inputListFormat() + " | " + this.dateTime;
    }
}