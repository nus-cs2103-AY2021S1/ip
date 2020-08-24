package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    String time;
    LocalDate dateTime;
    boolean isParsedDate;

    public Deadline(String line) throws EmptyStringException{
        super();
        if(line.isBlank()){
            throw new EmptyStringException("Deadline cannot be empty.");
        }
        String[] command = line.split(" \\/by ");
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
        String dateString = isParsedDate ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : time;
        return super.toString() + " (by: "
                + dateString
                + ")";
    }
}
