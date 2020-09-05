package exceptions;

public class InvalidTaskException extends DukeException{
    public InvalidTaskException(){
        super("Invalid task provided");
    }
}
