
public class Event extends Task {

    protected String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
        this.tag = "E";
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return String.format("[%s][%s] %s (at: %s)",tag,symbol,taskName,at);
    }
}