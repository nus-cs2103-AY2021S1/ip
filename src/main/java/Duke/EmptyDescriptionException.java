package Duke;

public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException(String message){
        super("The description of '" + message + "' should not be empty.\n"
                +"Please re-enter your command.");
    }
}
