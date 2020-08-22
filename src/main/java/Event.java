public class Event extends Task {

    private String startTime;

    public static Event createNewEvent(String argument) throws DukeException {
        String[] eventArguments = argument.split(" /at ");

        if (eventArguments.length != 2) {
            throw new DukeException("Invalid arguments for a new event.");
        }

        String eventName = eventArguments[0];
        if (eventName.isBlank()) {
            throw new DukeException("Event name cannot be blank!");
        }

        String startTime = eventArguments[1];
        if (startTime.isBlank()) {
            throw new DukeException("Event time cannot be blank!");
        }

        return new Event(eventName, startTime);
    }

    private Event(String taskName, String startTime) {
        super(taskName);
        this.startTime = startTime;
    }

    @Override
    public String generateStorageString() {
        return String.format("EVENT | %s | %s /at %s", isDone ? "TRUE" : "FALSE", taskData, startTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startTime);
    }
}
