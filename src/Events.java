public class Events extends Task {
    protected String time;

    public Events(String description, String time) {
        super(description);
        this.time = time;
    }

    public Events(String description, String time, Boolean isDone) {
        super(description, isDone);
        this.time = time;
    }


    @Override
    public String toString() {
        return "[E]" + this.getIcon() + description + " (at: " + time + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s", super.doneString(), this.description, this.time);
    }


}
