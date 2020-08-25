public class TimedTask extends Task {
    protected String time;

    public TimedTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.description + " | " + this.time;
    }
}