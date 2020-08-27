public class Event extends Task {
    protected String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
        typeOfTask = TypeOfTask.EVENT;
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() +  " (at: " + at + ")";
    }
}
