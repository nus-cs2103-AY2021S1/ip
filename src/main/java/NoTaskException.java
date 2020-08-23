package main.java;

public class NoTaskException extends DukeException {
    NoTaskException() {
        super("You haven't add any task!");
    }
}
