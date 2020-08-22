public class EventTask extends Task {
    String time;
    public EventTask(String name, String time) {
        super(name);
        this.time = time;
    }

    public EventTask(String name, int hasCompleted, String time) {
        super(name, hasCompleted);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")" ;
    }
}
