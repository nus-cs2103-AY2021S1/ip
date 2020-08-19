public class Events extends Task {
    protected String time;

    public Events(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + this.getIcon() + description + " (at: " + time + ")";
    }

}
