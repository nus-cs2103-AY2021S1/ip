package duke.types;

/**
 * Encapsulate a ActionType enum class that represents the type of actions of a command.
 * The class includes three action types: delete, done and find
 */
public enum ActionType {
    DELETE("delete"),
    DONE("done"),
    FIND("find");

    private final String type;

    /**
     * Constructs a ActionType object with the type of action given.
     *
     * @param actionType a string representing the type of action.
     */
    ActionType(String actionType) {
        type = actionType;
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
