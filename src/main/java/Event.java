/**
 * Represents an event which is a type of task that starts at a specific time
 * and ends at a specific time
 */
public class Event extends Task{
    private String duration;

    /**
     * Creates an Event object.
     * 
     * @param description the description of the Event task
     * @param duration the duration of the Event task
     * @throws PandaBotException If description or duration given is empty
     */
    public Event(String description, String duration) throws PandaBotException {
        super(description);
        
        String d = duration.strip();
        if (d.length() == 0) {
            throw new PandaBotInsufficientArgumentException();
        } else {
            this.duration = d;
        }
    }

    /**
     * Returns a String representation of the task that is displayed to the user
     *
     * @return a String Representation of the task that is displayed to the user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }

    /**
     * Returns a String representation of the task for saving to the save file 
     *
     * @return a String representation of the task for saving to the save file
     */
    @Override
    public String saveAsText() {
        return "E | " + super.saveAsText() + " | " + duration;
    }
}
