public class Event extends Task {

    private String taskDateTime;

    public Event(String desc, String taskDateTime) {
        super(desc);
        this.taskDateTime = taskDateTime;
    }

    public String getSaveToFileString() {
        return "E`" + super.getSaveToFileString() + "`" + taskDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.taskDateTime);
    }
}
