package duke.exceptions;

/**
 * Represents the exception from the deadline function
 * when the function receives a command in the wrong format.
 */
public class DeadlineException extends DukeException{

    public DeadlineException (){
        super("Please key in the deadline in the following format:\n" +
                "\'deadline task name /at date\' \n" +
                "With the date in the following format :" +
                "YYYY-MM-DD HHMM OR YYYY-MM-DD");
    }

    @Override
    public String toString(){
        return "Error";
    }
}
