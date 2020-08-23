public class Event extends Task implements Saveable {
    String timing;

    Event(String label, String timing, boolean done) {
        super(label, done);
        // Remove the "at"
        this.timing = timing;
    }

    @Override
    public String getInfo() {
        StringBuilder str = new StringBuilder();
        str.append("E");
        str.append(super.getInfo());
        str.append(super.separator);
        str.append(timing);
        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }
}
