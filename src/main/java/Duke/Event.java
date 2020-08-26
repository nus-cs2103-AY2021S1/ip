package Duke;

public class Event extends Task {
    private String duration;
    Event(String task, String duration){
        super(task);
        this.duration = duration;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (" + duration + ")";
    }
}
