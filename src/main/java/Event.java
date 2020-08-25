import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task {

    protected LocalDate atDate;
    protected LocalTime atTime;

    private Event(String taskName, String taskDateTime) throws DukeException {
        super(taskName);
        this.tag = "E";
        parseTime(taskDateTime.trim().replace("/", "-"));
    }

    public static Event create(String taskDescription) throws DukeException{
        if(!taskDescription.contains("/at")){
            throw new DukeException("Please include '/at' in front of the event time period");
        }
        String[] NameTimePair = taskDescription.split(" /at");
        String taskName = NameTimePair[0];
        String taskTime = NameTimePair[1];
        return new Event(taskName,taskTime);
    }


    public static Event create(String taskName, String taskTime) throws DukeException {
        return new Event(taskName, taskTime);
    }


    private void parseTime(String taskDateTime) throws DukeException {
        String[] dateTime = taskDateTime.replace("/","-").split(" ",2);
        try{
            this.atDate = LocalDate.parse(dateTime[0]);
        }catch(DateTimeParseException e){
            throw new DukeException("please enter a valid yyyy-mm-dd format");
        }

        try{
            if(dateTime.length == 2) {
                this.atTime = LocalTime.parse(dateTime[1]);
            }
        }catch(DateTimeParseException e){
            throw new DukeException("please enter a valid HH:MM format");
        }
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        if(atTime != null) {
            return String.format("[%s][%s] %s (at: %s %s)", tag, symbol, taskName
                    , atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), atTime.toString());
        }else{
            return String.format("[%s][%s] %s (at: %s)", tag, symbol, taskName
                    , atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }

    @Override
    public String safeFileFormat(){
        int done = isDone ? 1 : 0;
        if(atTime == null) {
            return String.format("%s | %d | %s | %s \n", tag, done, taskName, atDate.toString());
        }else{
            return String.format("%s | %d | %s | %s %s \n", tag, done, taskName, atDate.toString(), atTime.toString());
        }
    }

}