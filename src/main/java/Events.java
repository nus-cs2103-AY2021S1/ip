public class Events extends Task {

    protected String timing;

    public Events(String taskDesc, String timing) {
        super(taskDesc);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timing.substring(3) + ")";
    }
}
