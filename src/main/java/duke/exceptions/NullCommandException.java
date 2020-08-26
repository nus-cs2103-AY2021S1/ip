package duke.exceptions;

public class NullCommandException extends Exception{
    public NullCommandException(String err){
        super(err);
    }

    @Override
    public String getMessage(){
        return "🙈OOPS!!! The command cannot be empty";
    }

}
