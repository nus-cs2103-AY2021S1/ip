public class Event extends Task {

    private String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String toTxtFormat() {
        return "E | " + super.toTxtFormat() + " | " + this.dateTime;
    }

    public static Event parse(String txtFormat, String[] txtArray) {
        Event event = new Event(txtArray[2].trim(), txtArray[3].trim());
        if (txtArray[1].trim().equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }
}
