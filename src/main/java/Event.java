public class Event extends Task{

    private final String end;

    public Event(String name, boolean isDone, String end){
        super(name, isDone);
        this.end = end;
    }

    @Override
    public Task setToTrue(){
        return new Event(this.name, true, this.end);
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String getEnd(){
        return this.end;
    }

    @Override
    public String toString(){
        return isDone
                ? "[E][✓] " + this.getName() + " (by: " + this.end + ")"
                : "[E][✗] " + this.getName() + " (by: " + this.end + ")";
    }
}
