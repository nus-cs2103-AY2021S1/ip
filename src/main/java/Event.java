public class Event extends Task {
    String time;

    Event(String s, String time) {
        super(s);
        this.time = time;
    }

    Event(String name, boolean completed, String time) {
        super(name, completed);
        this.time = time;
    }

    @Override
    String getType() {
        return "event";
    }

    @Override
    String getTime() {
        return time;
    }

    @Override
    Event completeTask() {
        return new Event(this.name, true, this.time);
    }

    @Override
    public String toString() {
        return "[event]" + super.toString() + " (at: " + this.time + ")";
    }
}
