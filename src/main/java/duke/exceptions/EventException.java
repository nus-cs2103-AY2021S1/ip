package duke.exceptions;

/**
 * Represents the exception from the event function
 * when the function receives a command in the wrong format.
 */
public class EventException extends DukeException{
    public EventException(){
        super("Please key in the event in the following format:\n" +
                "\'event task name /at date\' \n" +
                "With the date in the following format :" +
                "YYYY-MM-DD HHMM OR YYYY-MM-DD");
    }

    @Override
    public String toString(){
        return "Error";
    }
}
