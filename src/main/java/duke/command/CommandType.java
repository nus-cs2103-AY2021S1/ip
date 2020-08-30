package duke.command;

/**
 * The CommandType class is an enumerator class and it describes
 * the different types of command and enumerates them.
 */
public enum CommandType {
    LIST("list"),
    LIST_DATE("list date"),
    ADD_TODO("todo"),
    ADD_DEADLINE("deadline"),
    ADD_EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    EXIT("exit"),
    FIND("find");

    private String type;

    /**
     * Takes in the name of the type of the command and returns a command type.
     *
     * @param type The type of the command.
     */
    CommandType(String type) {
        this.type = type;
    }

    /**
     * Takes in the command types and returns the names of the command types.
     *
     * @param types The command tpyes.
     * @return The names of the command types.
     */
    public static String[] getTypeName(CommandType ... types) {
        String[] typeNames = new String[10];
        for (int i = 0; i < types.length; i = i + 1) {
            typeNames[i] = types[i].type;
        }
        return typeNames;
    }
}
