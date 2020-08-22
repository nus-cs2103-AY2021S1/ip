public class EventTask extends Task {
    private String date;

    public EventTask(String name, String date) {
        super(name);
        this.date = date;
    }

    public EventTask(String line, boolean isAutomated) {
        super(line, true);
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.getDate() + ")";
    }
}
