package duke.command;

/**
 * Represents the list of supported command keywords.
 */
public enum CommandKey {
    DONE("done"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    EXIT("bye"),
    FIND("find");
    
    private String key;
    
    CommandKey(String key) {
        this.key = key;
    }
    
    String getKey() {
        return key;
    }

    /**
     * Checks if the user's command is valid.
     * 
     * @param userCommand User's command keyword (first word in the user's input).
     * @param key Supported command key.
     * @return Whether user's command keyword is supported.
     */
    public static boolean equalsCommandKey(String userCommand, CommandKey key) {
        return key.getKey().equals(userCommand);
    }
}
