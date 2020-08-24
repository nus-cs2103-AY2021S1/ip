package Logic.Exceptions;

public class DukeException extends Exception {

    public DukeException(String error){
        super(error);
    }

    @Override
    public String getMessage() {
        String breakLine = "\n    ___________________________________________________________________";
        return breakLine + "\n" + super.getMessage() + breakLine;
    }
}
