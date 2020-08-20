package Exceptions;

public class DukeIOException extends DukeException{
    DukeIOException(String bad_cmd){
        super(bad_cmd, 0);
    }
}
