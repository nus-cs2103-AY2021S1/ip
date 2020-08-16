package main.java;

public class DoneException extends Exception {
    DoneException() {
        super("☹ OOPS!!! You need a task number to use done!");
    }
}
