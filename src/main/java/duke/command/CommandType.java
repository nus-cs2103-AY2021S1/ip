package main.java.duke.command;

public enum CommandType {
    LIST("list"), LIST_DATE("list date"), ADD_TODO("todo"), ADD_DEADLINE("deadline"), ADD_EVENT("event"), DONE("done"), DELETE("delete"), EXIT("exit");

    String type;

    CommandType(String type) {
        this.type = type;
    }
}
