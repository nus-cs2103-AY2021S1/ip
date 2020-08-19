public class Event extends Task {

    public String type;

    public Event(String desc, boolean isDone) {
        super(desc, isDone);
        type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }
}
