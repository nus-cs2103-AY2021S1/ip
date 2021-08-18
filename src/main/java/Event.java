public class Event extends Task{
    private String timeline;
    String TASKINDICATOR = "[E]";

    public Event(String userInput, String timeline) {
        super(userInput);
        this.timeline = timeline;
    }

    public String printName() {
        return TASKINDICATOR + super.printName() + "(at: " + this.timeline + ")";
    }
}