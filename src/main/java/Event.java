public class Event extends Task{

    String time;

    public Event(String task, String time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        if (done) {
            return String.format ("[E][\u2713] %s(at: %s)", this.task, this.time);
        } else {
            return String.format ("[E][\u2718] %s(at: %s)", this.task, this.time);
        }
    }
}
