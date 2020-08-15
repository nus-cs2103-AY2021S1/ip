public class Event extends Task {

    String date;

    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

}
