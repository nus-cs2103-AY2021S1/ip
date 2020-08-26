package duke.exceptions;

public class LackOfTimeException extends Exception {
    private String typeOfCommand;
    public LackOfTimeException(String err, String typeOfCommand){
        super(err);
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public String getMessage(){
        return "⚠️OOPS!!! The time/date of " + this.typeOfCommand + " cannot be empty";
    }
}
