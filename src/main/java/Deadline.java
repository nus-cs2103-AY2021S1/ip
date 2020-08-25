import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {

    /** Date of the task **/
    protected LocalDate byDate;
    /** Time of the task **/
    protected LocalTime byTime;

    private Deadline(String taskName, String taskDateTime) throws DukeException {
        super(taskName);
        this.tag = "D";
        parseTime(taskDateTime.trim().replace("/", "-"));
    }

    /**
     * Creates Deadline task
     *
     * @param taskDescription description of the task
     * @return Deadline task
     * @throws DukeException if the format of the task description is wrong
     */
    public static Deadline create(String taskDescription) throws DukeException {
            if(!taskDescription.contains("/by")){
                throw new DukeException("Please include '/by' in front of the deadline");
            }
            String[] NameTimePair = taskDescription.split(" /by");
            String taskName = NameTimePair[0];
            String taskDateTime = NameTimePair[1];
            return new Deadline(taskName,taskDateTime);
    }

    /**
     * Converts string date/time in LocalDate and LocalTime
     * Stores them into the LocalDate and LocalTime variable
     *
     * @param taskDateTime the string representation of the time and date
     * @throws DukeException if the format of the task date/time is wrong
     */
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


    public static Deadline create(String taskName, String taskTime) throws DukeException {
        return new Deadline(taskName,taskTime);
    }

    /**
     * return the summarised form of the task
     *
     * @return String format of the summarised details of the task
     */
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

    /**
     * return the summarised form of the task in the format to be saved
     *
     * @return String format of the summarised details of the task to be saved
     */
    @Override
    public String safeFileFormat(){
        int done = isDone ? 1 : 0;
        if(byTime == null) {
            return String.format("%s | %d | %s | %s \n", tag, done, taskName, byDate.toString());
        }else{
            return String.format("%s | %d | %s | %s %s \n", tag, done, taskName, byDate.toString(), byTime.toString());
        }
    }
}