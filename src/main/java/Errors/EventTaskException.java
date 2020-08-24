package Errors;

import Errors.DukeException;

public class EventTaskException extends DukeException {
    public EventTaskException() {
        super("☹ OOPS!!! Please include a /at");
    }
}