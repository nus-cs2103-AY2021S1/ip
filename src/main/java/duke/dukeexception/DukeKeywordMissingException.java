package duke.dukeexception;

import duke.dukeexception.DukeException;

public class DukeKeywordMissingException extends DukeException {
    public DukeKeywordMissingException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "☹ OOPS!!! Please include " + this.getMessage() + " in your input.";
    }
}
