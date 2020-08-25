public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        tag = "E";
    }

    public Event(String description) {
        super(description);
        this.at = null;
        tag = "E";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return at == null
                ? super.toPrint()
                : super.toPrint() + "|" + at;
    }

    @Override
    public String toString() {
        return at == null ? "[E]" + super.toString() : "[E]" + super.toString() + " (by: " + at + ")" ;
    }
}