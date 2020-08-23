public class Events extends Task {

    String calender;

    public Events(String description, String calender) {
        super(description);
        this.calender = calender;
    }

    public Events(String description, String calender, boolean isDone) {
        super(description, isDone);
        this.calender = calender;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + calender + ")";
    }

    @Override
    public String writeToFile() { return "E" + super.writeToFile() + " | " + calender; }
}
