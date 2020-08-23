package duke.dukeexception;

import duke.dukeexception.DukeException;

public class DukeTaskNonExistException extends DukeException {

    public DukeTaskNonExistException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! This task does not exist!";
    }
}
