package Task;

import Task.Task;

public class Event extends Task {
    private String time;
    public Event(String content, String time) {
        super(content);
        this.time = time;
    }
    public String getDeadline() { return this.time; }
    @Override
    public String returnStringForm() {
        return "[E]" + super.returnStringForm() + " (at: " + this.time + ")";
    }
}
