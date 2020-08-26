public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone){
        super(description, isDone);
        this.at = at;
    }

    public String getTime(){
        return this.at;
    }

    @Override
    public String getOriginal() {
        return "event " + getTask() + " /at " + getTime();
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
