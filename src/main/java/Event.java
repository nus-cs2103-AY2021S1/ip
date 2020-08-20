public class Event extends Task {
    protected String dateAndTime;
    Event(String description, String dateAndTime, TaskType taskType) {
        super(description, taskType);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[Event]" + super.toString() + "(at: " + dateAndTime + ")";
    }
}
