package duke.enums;

import java.util.HashMap;

public enum CommandEnum {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DELETE("delete"),
    FIND("find"),
    INVALID;

    private static HashMap<String, CommandEnum> lookup = new HashMap<String, CommandEnum>();
    private String label;

    CommandEnum() {}

    CommandEnum(String commandEnum) {
        this.label = commandEnum;
    }

    static {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            lookup.put(commandEnum.getLabel(), commandEnum);
        }
    }

    private String getLabel() {
        return this.label;
    }

    public static CommandEnum stringToEnum(String commandEnum) {
        if (lookup.containsKey(commandEnum)) {
            return lookup.get(commandEnum);
        } else {
            return CommandEnum.INVALID;
        }
    }
}
