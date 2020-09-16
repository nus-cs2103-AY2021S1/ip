package task;

import duke.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String time;
    private LocalDate dateTime;
    private boolean isParsedDate;

    public Event(String line) throws EmptyStringException{
        super();
        if(line.isBlank()){
            throw new EmptyStringException("Event cannot be empty.");
        }
        String[] data = Parser.splitTime(line);
        this.item = data[0];
        this.time = data[1];
        this.isParsedDate = false;
        try {
            dateTime = Parser.parseTime(this.time);
            this.isParsedDate = true;
        }
        catch (Exception e){
            //Date failed to parse
        }
        taskType = "E";
    }

    @Override
    public String encode() {
        String encoded = "event " + item + " /at " + time;
        if(this.isDone){
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
