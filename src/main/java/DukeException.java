
public class DukeException extends Exception{

    String errorMessage;

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
