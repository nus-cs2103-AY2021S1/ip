package main.java;

public class DoneException extends DukeException {
    DoneException() {
        super("☹ OOPS!!! You need a task number to use done!");
    }
}
