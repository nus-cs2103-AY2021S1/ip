public class Event extends Task {

    private String duration;

    public Event (String task, String duration) {
        super(task);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.duration);
    }

}

