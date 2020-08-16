public class Events extends Task {
    protected String on;

    public Events(String description, String on) {
        super(description);
        this.on = on;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.on + ")";
    }


}
