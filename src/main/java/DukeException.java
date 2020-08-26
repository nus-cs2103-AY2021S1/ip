/**
 *Exceptions that may occur during users' entering arguments and commands.
 */
public class DukeException extends IllegalArgumentException{

    public DukeException(String message){
        super(message);
    }

    public DukeException(String message, Throwable cause){
        super(message, cause);
    }

    @Override
    public String toString(){
        return this.getMessage();
    }

}
