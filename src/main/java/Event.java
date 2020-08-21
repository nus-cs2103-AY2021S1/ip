public class Event extends Task{

    protected String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
