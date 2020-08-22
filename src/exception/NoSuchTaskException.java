package exception;

import exception.DukeException;

public class NoSuchTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The index provided is not within the task list";
    }
}
