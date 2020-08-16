public class Event extends Task{
    private String at;

    Event (String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + at + ")";
    }
}
