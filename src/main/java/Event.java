public class Event extends Task{
    String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return String.format("[E]%s", super.getStatusIcon(), time);
    }

    @Override
    public String getOutput() {
        return String.format("%s %s%s", getStatusIcon(), this.description, this.time);
    }

}