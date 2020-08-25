package duke;

import duke.exception.AmbiguousInputException;


import java.util.HashMap;
import java.util.Map;

/**
 * ValidCommand enum represents the possible command type.
 */
public enum ValidCommand {
    DELETE("delete","DELETE","del","DEL"),
    DEADLINE ("deadline","DEADLINE","ddl","DDL"),
    EVENT ("event","EVENT","eve","EVE"),
    TODO ("todo","TODO","to","TO"),
    CLEAR ("clear","CLEAR","clr","CLR"),
    EXIT ("bye","BYE","quit","QUIT","EXIT","exit"),
    FIND ("find","search","FIND","SEARCH"),
    DONE ("done","DONE","FINISH","finish"),
    LIST ("list","LIST","ls","LS");

    private final String[] aliases;
    private static final Map<String, ValidCommand> aliasMap = new HashMap<>();

    static {
        for (ValidCommand command : ValidCommand.values()) {
            for (String alias : command.aliases) {
                aliasMap.put(alias,command);
            }
        }
    }

    ValidCommand(String... aliases) {
        this.aliases = aliases;
    }

    /**
     * Returns a specific command regarding to user input.
     * @param alias User input.
     * @return A command name.
     * @throws AmbiguousInputException When system cannot recognize the command.
     */
    public static ValidCommand commandType(String alias) throws AmbiguousInputException {
        ValidCommand command = aliasMap.get(alias);

        if (command == null) {
            throw new AmbiguousInputException();
        }

        return command;
    }
}
