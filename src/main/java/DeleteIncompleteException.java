package main.java;

public class DeleteIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify which task to be deleted.";
    }
}
