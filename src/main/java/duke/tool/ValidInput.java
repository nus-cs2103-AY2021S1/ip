package duke.tool;

import duke.exceptions.NoSuchOrderException;

import java.util.HashMap;

public enum ValidInput {
    DEADLINE("deadline", "Deadline", "ddl", "DDL"),
    DELETE("delete", "Delete", "clear", "clr"),
    DONE("done", "Done", "finish", "complete"),
    EVENT("event", "Event", "EVENT", "E"),
    EXIT("bye", "quit", "exit"),
    FIND("find", "FIND", "fnd"),
    LIST("list", "lst"),
    TASK("task"),
    TODO("todo", "TODO");

    private final String[] forms;

    ValidInput(String...strings) {
        this.forms = strings;
    }

    private static HashMap<String, ValidInput> commandMap = new HashMap<>();

    static {
        for (ValidInput input : ValidInput.values()) {
            for (String cmd : input.forms) {
                commandMap.put(cmd, input);
            }
        }
    }

    public static ValidInput getCmdType(String cmd) throws NoSuchOrderException {
        ValidInput command = commandMap.get(cmd);

        if(command == null) {
            throw new NoSuchOrderException();
        }

        return command;
    }

}
