package Task;

public class Event extends Task {

    /** The time of the event **/
    private final String end;

    /**
     * Initializes event task
     * @param name
     * @param isDone
     * @param end
     */
    public Event(String name, boolean isDone, String end){
        super(name, isDone);
        this.end = end;
    }

    /** Set the current event to done **/
    @Override
    public Task setToTrue(){
        return new Event(this.name, true, this.end);
    }

    /** Get the type of the current task **/
    @Override
    public String getType(){
        return "E";
    }

    /** Get the specific time when the event is going to be held **/
    @Override
    public String getEnd(){
        return this.end;
    }

    /** Convert the current task to String **/
    @Override
    public String toString(){
        return isDone
                ? "[E][✓] " + this.getName() + " (by: " + this.end + ")"
                : "[E][✗] " + this.getName() + " (by: " + this.end + ")";
    }

    /** Override the equals from Object so that it can be used to handle event **/
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        } else if (o instanceof Event){
            Event temp = (Event) o;
            return this.name.equals(temp.name) && (this.isDone == temp.isDone) && this.end.equals(temp.end);
        } else {
            return false;
        }
    }
}
