package duck;

public class Event extends Task {

    private String date;

    public Event(String desc, String date) {
        super(desc);
        this.date = date;
    }

    @Override
    public String getStatus() {
        return Colour.Cyan("[E]") + super.getStatus() + " (at: " + this.date + ")";
    }
}
