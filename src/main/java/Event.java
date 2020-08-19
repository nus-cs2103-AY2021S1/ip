public class Event extends Task{
    private String duration;
    public Event (String name,boolean isCompleted, String duration) {
        super(name,isCompleted);
        this.duration = duration;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getTaskSymbol() {
        return "[E]";
    }

    public String toString () {
        return String.format("%s (at: %s)",this.description,this.duration);
    }
}
