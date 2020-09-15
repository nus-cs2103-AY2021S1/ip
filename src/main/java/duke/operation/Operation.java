package duke.operation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Operation {
    HELP("help", "h"),
    BYE("bye", "seeya"),
    FIND("find", "f"),
    LIST("list", "ls"),
    DONE("done"),
    DELETE("delete", "remove", "del"),
    TODO("todo", "t"),
    DEADLINE("deadline", "dl"),
    EVENT("event","e"),
    UNKNOWN();

    static final private Map<String, Operation> ALIAS_MAP = new HashMap<>();
    static {
        for (Operation operation : Operation.values()) {
            for (String alias : operation.aliases) {
                ALIAS_MAP.put(alias.toUpperCase(), operation);
            }
        }
    }

    static public Operation toOperation(String command) {
        assert command != null : "Command passed to Enum is null.";
        Operation operation = ALIAS_MAP.get(command.toUpperCase());
        if (operation == null) {
            return Operation.UNKNOWN;
        }
        return operation;
    }

    private List<String> aliases;
    Operation(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }
}
