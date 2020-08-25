package duke;

import java.util.HashMap;

public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DELETE("delete"),
    INVALID;

    private static HashMap<String, Command> lookup = new HashMap<String, Command>();
    private String label;

    Command() {}

    Command(String command) {
        this.label = command;
    }

    static {
        for (Command command : Command.values()) {
            lookup.put(command.getLabel(), command);
        }
    }

    private String getLabel() {
        return this.label;
    }

    static Command stringToEnum(String command) {
        if (lookup.containsKey(command)) {
            return lookup.get(command);
        } else {
            return Command.INVALID;
        }
    }
}
