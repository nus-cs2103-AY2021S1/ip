package task;

public class Event extends Task{
    String time;
    public Event(String line) throws EmptyStringException{
        super();
        if(line.isBlank()){
            throw new EmptyStringException("Event cannot be empty.");
        }
        String[] command = line.split(" \\/at ");
        this.item = command[0];
        this.time = command[1];
        taskType = "E";
    }

    @Override
    public String encode() {
        String encoded = "event " + item + " \\/at " + time;
        if(this.done){
            encoded = encoded + "\n" + "done";
        }
        return encoded;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
