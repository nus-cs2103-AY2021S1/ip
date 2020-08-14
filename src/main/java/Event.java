public class Event extends Task {
    private String time;
    Event(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String returnStringForm() {
        return "[E]" + super.returnStringForm() + "(at: " + this.time + ")";
    }
}
