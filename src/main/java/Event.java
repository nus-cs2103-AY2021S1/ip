public class Event extends Task {

    private String startTime;
    
    public static Event createNewEvent(String argument) {
        String[] eventArguments = argument.split(" /at ");
        
        String eventName = eventArguments[0];
        String startTime = eventArguments[1];
        
        return new Event(eventName, startTime);
    }

    private Event(String taskName, String startTime) {
        super(taskName);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startTime);
    }
}
