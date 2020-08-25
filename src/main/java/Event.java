public class Event extends Task {
    protected String eventTime;

    Event(String desc) throws MissingDeadlineException {
        super("E", desc.split(" /at ", 2)[0]);
        String[] temp = desc.split(" /at ", 2);
        if (temp.length == 1) {
            throw new MissingDeadlineException("event");
        } else {
            this.eventTime = desc.split(" /at ", 2)[1];
        }
    }

    @Override
    public String formatTaskForFile() {
        return this.taskType + " | " + (this.isDone ? "1" : "0") + " | " +
                this.description + " | " + this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description + " (at: " + this.eventTime + ")";
    }
}
