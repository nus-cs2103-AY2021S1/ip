package duke.types;

/**
 * Encapsulate a ActionType enum class that represents the actions of a command.
 */
public enum ActionType {
    DELETE("delete"),
    DONE("done"),
    FIND("find");

    private final String type;

    /**
     * Constructs a ActionType object with the type given.
     *
     * @param type a string representing the type.
     */
    ActionType(String type) {
        this.type = type;
    }

    /**
     * Gets the ActionType object from a string representation of the type.
     * Returns null if no such object exists.
     *
     * @param type a string representing the type.
     * @return an ActionType object of the specified type and null if no such object exists.
     */
    public static ActionType valueOfType(String type) {
        for (ActionType e : values()) {
            if (e.type.equals(type)) {
                return e;
            }
        }
        return null;
    }
}
