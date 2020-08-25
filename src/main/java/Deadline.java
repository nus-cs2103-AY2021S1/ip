import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    private Deadline(String taskName, String taskDateTime) throws DukeException {
        super(taskName);
        this.tag = "D";
        parseTime(taskDateTime.trim().replace("/", "-"));
    }

    public static Deadline create(String taskDescription) throws DukeException {
            if(!taskDescription.contains("/by")){
                throw new DukeException("Please include '/by' in front of the deadline");
            }
            String[] NameTimePair = taskDescription.split(" /by");
            String taskName = NameTimePair[0];
            String taskDateTime = NameTimePair[1];
            return new Deadline(taskName,taskDateTime);
    }

    private void parseTime(String taskDateTime) throws DukeException {
         String[] dateTime = taskDateTime.replace("/","-").split(" ",2);
         try{
             this.byDate = LocalDate.parse(dateTime[0]);
         }catch(DateTimeParseException e){
             throw new DukeException("please enter a valid yyyy-mm-dd format");
         }

         try{
             if(dateTime.length == 2) {
                 this.byTime = LocalTime.parse(dateTime[1]);
             }
         }catch(DateTimeParseException e){
             throw new DukeException("please enter a valid HH:MM format");
         }
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        if(byTime != null) {
            return String.format("[%s][%s] %s (by: %s %s)", tag, symbol, taskName
                                , byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), byTime.toString());
        }else{
            return String.format("[%s][%s] %s (by: %s)", tag, symbol, taskName
                                , byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
    }
}