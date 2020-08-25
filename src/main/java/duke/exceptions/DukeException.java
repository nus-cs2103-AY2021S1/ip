package duke.exceptions;

import duke.utils.Colour;

public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return Colour.Red(this.getMessage());
    }
}
