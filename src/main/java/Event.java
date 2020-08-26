public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    public static Event decode(String saved) throws AliceException {
        String[] inputs = saved.split(" \\| ");
        boolean isDone = inputs[0].equals("1");
        if (inputs.length == 3) {
            return new Event(isDone, inputs[1], inputs[2]);
        } else {
            throw new AliceException("Corrupted Event data");
        }
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
