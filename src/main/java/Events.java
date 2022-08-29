public class Events extends Task {

    protected String Eventsdeadlineby;

    public Events(String description) {
        super(description);
    }

    public Events(String description,String Eventsdeadlineby) {
        super(description);
        this.Eventsdeadlineby = Eventsdeadlineby;
    }

    public String getEventsDescription() {
        return description;
    }

    public String getEvent() {
        return this.Eventsdeadlineby;
    }

    public String getItem() {
        return "[E]";
    }



}
