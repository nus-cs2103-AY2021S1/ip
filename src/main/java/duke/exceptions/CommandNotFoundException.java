package duke.exceptions;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String err){
        super(err);
    }

    @Override
    public String getMessage(){
        return "🙈 OOPS!!! Sorry I don't understand what that means";
    }
}
