package main.java;

public class CommandNotFoundException extends DukeException{
    public CommandNotFoundException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}