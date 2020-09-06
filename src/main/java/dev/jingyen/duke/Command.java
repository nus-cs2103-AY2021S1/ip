package dev.jingyen.duke;

public enum Command {
    LIST("list"),
    FIND("find"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private String commandString;

    Command(String commandString) {
        this.commandString = commandString;
    }
}
