package rock.utility;

import rock.tag.CommandTag;

/**
 * Parse command into specific command type for handling.
 */
public class Parser {

    public enum CommandType {
        LIST,
        DONE,
        TODO,
        DEADLINE,
        BYE,
        EVENT,
        DELETE,
        DO_WITHIN,
        FIND,
        INVALID,
        SWITCH;
    }

    public Parser() {

    }

    /**
     * Parse user's commands to CommandType that other method can understand.
     * @param cmd User's command.
     * @return Command type.
     */
    public static CommandType getType(String cmd) {
        if (cmd.equals(CommandTag.LIST)) {
            return CommandType.LIST;
        } else if (cmd.equals(CommandTag.BYE)) {
            return CommandType.BYE;
        } else if (cmd.startsWith(CommandTag.DONE + " ")) {
            return CommandType.DONE;
        } else if (cmd.startsWith(CommandTag.TODO + " ")) {
            return CommandType.TODO;
        } else if (cmd.startsWith(CommandTag.DEADLINE + " ")) {
            return CommandType.DEADLINE;
        } else if (cmd.startsWith(CommandTag.EVENT + " ")) {
            return CommandType.EVENT;
        } else if (cmd.startsWith(CommandTag.DELETE + " ")) {
            return CommandType.DELETE;
        } else if (cmd.startsWith(CommandTag.FIND + " ")) {
            return CommandType.FIND;
        } else if (cmd.startsWith(CommandTag.DO_WITHIN + " ")) {
            return CommandType.DO_WITHIN;
        } else if (cmd.equals(CommandTag.SWITCH)) {
            return CommandType.SWITCH;
        }
        return CommandType.INVALID;
    }
}
