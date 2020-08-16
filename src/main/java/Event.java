public class Event extends Task{
    String schedule;

    Event(int ind, String description, String schedule) {
        super(ind, description);
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(Appear at: " + schedule + ")";
    }
}
