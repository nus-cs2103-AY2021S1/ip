package duke.dukeexception;

public class DukeIncorrectTimeFormatException extends DukeException {

    public DukeIncorrectTimeFormatException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "☹ OOPS!!! Ensure that the datetime input is in the format YYYY-MM-DD HH:MM";
    }
}

