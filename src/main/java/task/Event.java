package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    String time;
    LocalDate dateTime;
    boolean isParsedDate;

    public Event(String line) throws EmptyStringException{
        super();
        if(line.isBlank()){
            throw new EmptyStringException("Event cannot be empty.");
        }
        String[] command = line.split(" \\/at ");
        this.item = command[0];
        this.time = command[1];
        this.isParsedDate = false;
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            this.dateTime = LocalDate.parse(this.time, format);
            this.isParsedDate = true;
        }
        catch (Exception e){
            //Date failed to parse
        }
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
        String dateString = isParsedDate ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : time;
        return super.toString() + " (at: "
                + dateString
                + ")";
    }
}
