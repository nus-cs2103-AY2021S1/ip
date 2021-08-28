package duke;
/**
 * Events are tasks that have a timeline.
 *
 * @author Dominic Siew Zhen Yu
 */
public class Event extends Task{
    private String timeline;
    String TASKINDICATOR = "[E]";

    /**
     * The constructor of the events class.
     *
     * @param userInput the name of the task
     * @param timeline the period of which the event is taking place
     */
    public Event(String userInput, String timeline) {
        super(userInput);
        this.timeline = timeline;
    }

    /**
     * printName() method returns the name and the task indicator of the Event object.
     *
     * @return a string representation of a Event object
     */
    public String printName() {
        return TASKINDICATOR + " " + super.printName() + " (at: " + this.timeline + " )";
    }
}