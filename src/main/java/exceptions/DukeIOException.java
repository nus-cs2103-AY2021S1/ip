package exceptions;

public class DukeIOException extends DukeException{
    public DukeIOException(String bad_cmd){
        super(bad_cmd, 0);
    }
}
