package tasks;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getDate() {
        return at.substring(0, 11);
    }

    @Override
    public String saveString() {
        if (this.isDone) {
            return "T , 1 , " + description + " , " + at;
        }
        else {
            return "T , 0 , " + description + " , " + at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
