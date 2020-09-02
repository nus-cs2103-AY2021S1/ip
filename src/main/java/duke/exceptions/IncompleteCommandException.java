package duke.exceptions;

// Exception for incomplete/ inappropriate command given
public class IncompleteCommandException extends DukeException{

    public IncompleteCommandException(){
        super("Please enter an appropriate command!");
    }

    @Override
    public String toString(){
        return "Error";
    }
}
