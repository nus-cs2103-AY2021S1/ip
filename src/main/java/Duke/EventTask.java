package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task{
    private String taskType = "E";
    private LocalDateTime fromDateTime, toDateTime;
    private final String[] formatStyle = {
            "dd/MM/yyyy",
            "dd.MM.yyyy",
            "ddLLL,yyyy",
            "ddLLLyyyy",
            "yyyy-MM-dd",
            "dd/MM/yyyyHHmm",
            "dd.MM.yyyyHHmm",
            "ddLLL,yyyyHHmm",
            "ddLLLyyyyHHmm",
            "yyyy-MM-ddHHmm",
            "dd/MM/yyyyHH:mm",
            "dd.MM.yyyyHH:MM",
            "ddLLL,yyyyHH:mm",
            "ddLLLyyyyHH:mm",
            "yyyy-MM-ddHH:mm",
    };


    EventTask(String taskContent, String time) throws ParseErrorException{
        super(taskContent);
        boolean success = false;
        if (!time.contains("/to"))
            throw new ParseErrorException("The format of time should be \"TIME /to TIME\"");
        String fromTime = time.split("/to")[0].replaceAll("\\s","");
        String toTime = time.split("/to")[1].replaceAll("\\s","");
        for (String pattern: formatStyle){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            try{
                this.fromDateTime = LocalDateTime.parse(fromTime, formatter);
                success=true;
                break;
            } catch (DateTimeParseException e){
                continue;
            }
        }
        if (!success)
            throw new ParseErrorException("Please check your format again.\n You are suggested to use \"yyyy-MM-dd HH:mm\"");

        for (String pattern: formatStyle){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            try{
                this.toDateTime = LocalDateTime.parse(toTime, formatter);
                success=true;
                break;
            } catch (DateTimeParseException e){
                continue;
            }
        }
        if (!success)
            throw new ParseErrorException("Please check your format again.\n You are suggested to use \"yyyy-MM-dd HH:mm\"");
    }

    public LocalDateTime getFromDateTime(){
        return this.fromDateTime;
    }

    public LocalDateTime getToDateTime(){
        return this.toDateTime;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString() {
        return super.toString()+" (at: " + this.fromDateTime.toString()+ " - " + this.toDateTime.toString() + ")";
    }
}