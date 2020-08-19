public class Events extends Task {
    protected String time;

    public Events(String description, int num, String time) {
        super(description, num);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + this.getIcon() + description + " (at: " + time + ")";
    }

}
