public class Deadline extends Task {

    public Deadline(String description, boolean isDone, String timestamp) {
        super(description, "D", isDone, timestamp);
    }

    public static Deadline newDeadline(String raw) throws ChatbotException {
        if (raw.length() == 0) {
            throw new ChatbotException("Ooopsss (>.>) Deadline cannot be empty!!");
        }
        String description = raw.split("/by")[0].trim();
        String timestamp;
        try {
            timestamp = raw.split("/by")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException("Ooopsss (>.>) Deadline? By when??!!");
        }
        return new Deadline(description, false, timestamp);
    }

    @Override
    public Deadline markDone() {
        return new Deadline(this.description, true, this.timestamp);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.timestamp + ")";
    }
}
