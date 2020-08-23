public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, int isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString().replace("[\u2718]", "[E][\u2718]") + " (at: " + time + ")";
    }

    @Override
    public String deleteMessage() {
        return super.deleteMessage().replace("[\u2718]", "[T][\u2718]");
    }

    public String data() {
        return  "T" + super.data() + " | " + time;
    }
}
