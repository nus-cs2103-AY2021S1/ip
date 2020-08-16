package main.java;

public class NotACommandException extends Exception {
    NotACommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
