package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private LocalDateTime dateTime;
    private String taskType = "D";
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

    DeadlineTask(String taskContent, String time) throws ParseErrorException{
        super(taskContent);
        time = time.replaceAll("\\s","");
        boolean success = false;
        for (String pattern: formatStyle){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            try{
                this.dateTime = LocalDateTime.parse(time, formatter);
                success=true;
                break;
            } catch (DateTimeParseException e){
                continue;
            }
        }
        if (!success)
            throw new ParseErrorException("Please check your format again.\n You are suggested to use \"yyyy-MM-dd HH:mm\"");
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString() {
        return super.toString()+" (by: " + this.dateTime + ")";
    }
}