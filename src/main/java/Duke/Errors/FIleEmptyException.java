package Duke.Errors;

import Duke.Errors.DukeException;

public class FIleEmptyException extends DukeException {

    @Override
    public String toString() {
        return "Task file is empty!";
    }
}
