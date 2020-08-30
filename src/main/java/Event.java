public class Event extends Task {
    protected String time;

    public Event(String task_info,String time) {
        super(task_info);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (%s)", time);
    }

}