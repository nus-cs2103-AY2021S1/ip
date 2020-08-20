public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);

        return "[E]" + "[" + getStatusIcon() + "] " + desArray[0] + "(" + timeArray[0] + ": " + timeArray[1] + ")";
    }
}
