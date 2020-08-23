public class Event extends Task {

    public Event(String description, boolean isDone, String timestamp) {
        super(description, "E", isDone, timestamp);
    }

    public static Event newEvent(String raw) throws ChatbotException {
        if (raw.length() == 0) {
            throw new ChatbotException("Ooopsss (>.>) Event cannot be empty!!");
        }

        String description = raw.split("/at")[0].trim();
        String timestamp;

        try {
            timestamp = raw.split("/at")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException("Ooopsss (>.>) Event? At what date??!!");
        }
        return new Event(description, false, timestamp);
    }

    @Override
    public Event markDone() {
        return new Event(this.description, true, this.timestamp);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.timestamp + ")";
    }
}
