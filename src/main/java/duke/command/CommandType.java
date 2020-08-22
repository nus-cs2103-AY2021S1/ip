package main.java.duke.command;

/**
 * The CommandType class is an enumerater class and it describes
 * the different types of command and enumerates them.
 */
public enum CommandType {
    LIST("list"), LIST_DATE("list date"), ADD_TODO("todo"), ADD_DEADLINE("deadline"), ADD_EVENT("event"), DONE("done"), DELETE("delete"), EXIT("exit"), FIND("find");


    String type;

    /**
     * Takes in the name of the type of the command and returns a command type.
     *
     * @param type
     */
    CommandType(String type) {
        this.type = type;
    }
}
