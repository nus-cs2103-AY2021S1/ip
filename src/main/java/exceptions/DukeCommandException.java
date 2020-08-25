package exceptions;

public class DukeCommandException extends DukeException{
    
    public DukeCommandException(String bad_cmd){
        super(bad_cmd, 1);
    }
}
