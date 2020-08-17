package ChatbotPkg;

public class Deadline extends Task {

    String timestamp;

    public Deadline(String raw) {
        super(raw.split("/by")[0].trim(), "D");
        this.timestamp = raw.split("/by")[1].trim();
    }

    private Deadline(String description, boolean isDone, String timestamp) {
        super(description, "D", isDone);
        this.timestamp = timestamp;
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
