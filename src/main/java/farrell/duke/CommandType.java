package main.java.farrell.duke;

public enum CommandType {
    TODO,
    EVENT,
    DEADLINE,
    DONE,
    LIST,
    DELETE,
    BYE,
    UNDEFINED;

    public static CommandType enumFromString(String string) throws DukeException {
        try {
            return CommandType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new DukeException("I don't know what that means!");
        }
    }
}
