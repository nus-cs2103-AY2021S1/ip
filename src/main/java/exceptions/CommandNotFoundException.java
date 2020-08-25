package main.java.exceptions;

import main.java.exceptions.DukeException;

public class CommandNotFoundException extends DukeException {

    public CommandNotFoundException() {
        this("OOPS!!! I'm sorry, but I don't know what that means... (´∀`)\n" +
                "So far I support store different tasks, eg: todo, deadline, event. ٩(ˊᗜˋ*)و");
    }

    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
