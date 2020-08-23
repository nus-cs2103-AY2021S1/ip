public class Event extends Task {
    protected String duration;

    Event(String description, int id, String duration) {
        super(description, id);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " +
                this.description + "(" + this.duration + ")";
    }
}
