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
    
    public String storeFormat() {
        return String.format("%s %s %s %s",this.getTaskSymbol(),this.isDone(),this.description,this.duration);
    }

    public String toString () {
        return String.format("%s (at: %s)",this.description,this.duration);
    }
}
