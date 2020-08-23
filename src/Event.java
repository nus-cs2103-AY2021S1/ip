public class Event extends Task {
    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at){
        super(description);
        this.isDone = isDone;
        this.at = at;
    }

    public String saveText(){
        return "E | " + getStatusIcon() + " | " + description + " | " + at + "\n";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
