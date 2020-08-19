public class Events extends Task {

    String calender;

    public Events(String description, String calender) {
        super(description);
        this.calender = calender;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + calender + ")";
    }
}
