public class Event extends Task{
    protected String at;
    int code = 2;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static void invalidInput() {
        invalidInput("OOPS!!! The format of the Event is wrong.");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
