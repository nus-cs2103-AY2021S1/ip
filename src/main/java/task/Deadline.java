package task;

import duke.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String time;
    private LocalDate dateTime;
    private boolean isParsedDate;

    public Deadline(String line) throws EmptyStringException{
        super();
        if(line.isBlank()){
            throw new EmptyStringException("Deadline cannot be empty.");
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
        taskType = "D";
    }

    @Override
    public String encode() {
        String encoded = "deadline " + item + " /by " + time;
        if(this.isDone){
            encoded = encoded + "\n" + "done";
        }
        return encoded;
    }

    @Override
    public String toString() {
        String dateString = isParsedDate ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : time;
        return super.toString() + " (by: "
                + dateString
                + ")";
    }
}
