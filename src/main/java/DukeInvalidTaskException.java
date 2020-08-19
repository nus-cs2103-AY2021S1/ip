package main.java;

public class DukeInvalidTaskException extends DukeException {
    public DukeInvalidTaskException(){
        super("Task is invalid, description cannot be empty :(");
    }
}
