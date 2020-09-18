package duke.dukeexception;

import duke.dukeexception.DukeException;

public class InvalidInputException extends DukeException {
    public InvalidInputException(String e) {
        super("There's something wrong with your input for this... "
                + "Sigh, life is tough.\n\n" + e);
    }
}
