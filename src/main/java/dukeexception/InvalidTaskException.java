package main.java.duke.dukeexception;

import main.java.duke.dukeexception.DukeException;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Did you make up a task type? I don't understand what you mean...");
    }
}
