package task;

public class Event extends Task{
    String time;
    public Event(String line) {
        super();
        String[] command = line.split(" \\/at ");
        this.item = command[0];
        this.time = command[1];
        taskType = "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
