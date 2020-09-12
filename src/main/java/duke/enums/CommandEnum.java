package duke.enums;

import java.util.HashMap;

/**
 * A class that encapsulates the type of Command.
 */
public enum CommandEnum {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DELETE("delete"),
    FIND("find"),
    RESCHEDULE("reschedule"),
    INVALID;

    /**
     * Key is the String representation of the enum, and value is the enumeration.
     */
    private static HashMap<String, CommandEnum> lookup = new HashMap<String, CommandEnum>();

    /**
     * String representation of each enumeration value.
     */
    private String label;

    /**
     * Default constructor for the CommandEnum class.
     */
    CommandEnum() {}

    /**
     * Constructs the CommandEnum, with the label as commandEnum.
     *
     * @param commandEnum String representation of the enumeration.
     */
    CommandEnum(String commandEnum) {
        this.label = commandEnum;
    }

    /**
     * Static method that loads the HashMap with key-value pairs of all enumeration values.
     */
    static {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            lookup.put(commandEnum.getLabel(), commandEnum);
        }
    }

    /**
     * Getter method for the labels of the enumeration.
     * @return Label of the enumeration.
     */
    private String getLabel() {
        return this.label;
    }

    /**
     * Takes in the String representation of the enumeration and returns the corresponding enumeration.
     *
     * @param commandEnum String representation of the enumeration to return.
     * @return The value of the enumeration.
     */
    public static CommandEnum stringToEnum(String commandEnum) {
        if (lookup.containsKey(commandEnum)) {
            return lookup.get(commandEnum);
        } else {
            return CommandEnum.INVALID;
        }
    }
}
