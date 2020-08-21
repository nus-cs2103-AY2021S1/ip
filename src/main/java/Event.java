public class Event extends Task {

    private String time;

    Event(String name, String time) {
        super(name, Duke.TaskType.EVENT);
        this.time = time;
    }

    Event(String name, String time, boolean done) {
        super(name, Duke.TaskType.EVENT, done);
        this.time = time;
    }

    @Override
    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return this.taskType + " | " + doneString + " | " + this.name + " | " + this.time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        return "[E]" + "[" + doneString + "] " + this.name + " (at: " + this.time + ")";
    }
}
