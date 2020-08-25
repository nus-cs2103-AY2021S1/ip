package exceptions;

public class DukeNoInputException extends DukeException {
    public DukeNoInputException(String bad_cmd){
        super(bad_cmd,2);
    }
}
