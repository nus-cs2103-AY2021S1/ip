public class Event extends Task {

    protected String duration;

    public Event(String new_task, String duration) {
        super(new_task);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + duration + ")";
    }
}

