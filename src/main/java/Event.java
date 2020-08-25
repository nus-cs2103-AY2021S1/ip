public class Event extends Task{
    protected String time;

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + time + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + (this.isDone == true ? '1' : '0')  + " | " + this.taskDescription + "|" + this.time;
    }
}
