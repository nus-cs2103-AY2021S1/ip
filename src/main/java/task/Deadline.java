package task;

public class Deadline extends Task{
    String time;
    public Deadline(String line) throws EmptyStringException{
        super();
        if(line.isBlank()){
            throw new EmptyStringException("Deadline cannot be empty.");
        }
        String[] command = line.split(" \\/by ");
        this.item = command[0];
        this.time = command[1];
        taskType = "D";
    }

    @Override
    public String encode() {
        String encoded = "deadline " + item + " \\/by " + time;
        if(this.done){
            encoded = encoded + "\n" + "done";
        }
        return encoded;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + time + ")";
    }
}
