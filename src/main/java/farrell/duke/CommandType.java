package main.java.farrell.duke;

/**
 * Enumeration of the commands accepted by the program.
 * Unsupported commands will fall under UNDEFINED.
 */
public enum CommandType {
    TODO,
    EVENT,
    DEADLINE,
    DONE,
    LIST,
    DELETE,
    BYE,
    UNDEFINED;

    /**
     * Maps a string to its corresponding CommandType value.
     *
     * @param string The string to be mapped.
     * @return The CommandType value associated with the string.
     * @throws DukeException If the string cannot be mapped to a valid command.
     */
    public static CommandType enumFromString(String string) throws DukeException {
        try {
            return CommandType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new DukeException("I don't know what that means!");
        }
    }
}
