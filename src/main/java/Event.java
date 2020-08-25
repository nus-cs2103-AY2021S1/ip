import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task {

    /** Date of the task **/
    protected LocalDate atDate;
    /** Time of the task **/
    protected LocalTime atTime;

    private Event(String taskName, String taskDateTime) throws DukeException {
        super(taskName);
        this.tag = "E";
        parseTime(taskDateTime.trim().replace("/", "-"));
    }

    /**
     * Creates Event task
     *
     * @param taskDescription description of the task
     * @return Event task
     * @throws DukeException if the format of the task description is wrong
     */
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


    /**
     * return the summarised form of the task
     *
     * @return String format of the summarised details of the task
     */
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


    /**
     * return the summarised form of the task in the format to be saved
     *
     * @return String format of the summarised details of the task to be saved
     */
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