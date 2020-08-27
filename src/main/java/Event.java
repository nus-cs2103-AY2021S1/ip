public class Event extends Task{
    // Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
    private String duration;
    
    public Event(String description, String duration) throws PandaBotEmptyTaskDescriptionException {
        super(description);
        
        this.duration = duration.strip();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
    
    @Override
    public String saveAsText() {
        return "E | " + super.saveAsText() + " | " + duration;
    }
}
