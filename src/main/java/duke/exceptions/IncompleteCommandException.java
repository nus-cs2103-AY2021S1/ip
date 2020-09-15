package duke.exceptions;

/**
 * Represents the exception from an incomplete function
 * when the function receives an inappropriate command or
 * when the command is incomplete
 */
public class IncompleteCommandException extends DukeException{

    public IncompleteCommandException(){
        super("Please enter an appropriate command!");
    }

    @Override
    public String toString(){
        return "Error";
    }
}
