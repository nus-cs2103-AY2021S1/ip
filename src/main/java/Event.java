public class Event extends Task{
    private String time;
    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public String dataStorage() {
        return "E | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName() + " | " + this.time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
