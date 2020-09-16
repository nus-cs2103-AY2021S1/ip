package duke.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerates the types of commands supported by Duke.
 */
public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    LIST("list"),
    SORT("sort"),
    BYE("bye"),
    INVALID;

    private static final Map<String, CommandType> MAPPING = new HashMap<>();

    private final String type;

    CommandType() {
        this.type = null;
    }

    CommandType(String type) {
        this.type = type;
    }

    static {
        for (CommandType commandType : CommandType.values()) {
            MAPPING.put(commandType.type, commandType);
        }
    }

    /**
     * Returns string representation of the command type.
     * @return string representation of command type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the corresponding CommandType that matches its string representation.
     *
     * @param type The type of command.
     * @return The CommandType of that specific command.
     */
    public static CommandType getCommandType(String type) {
        CommandType commandType = MAPPING.get(type);
        if (commandType == null) {
            return INVALID;
        }
        return commandType;
    }
}
