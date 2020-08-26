package duke.Exceptions;

// Exception for incomplete/ inappropriate command given
public class IncompleteCommandException extends DukeException{

    public IncompleteCommandException(){
        super("Please enter an appropriate command!");
    }

    public String toString(){
        return "Error";
    }
}
