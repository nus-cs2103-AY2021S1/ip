package duke;

import duke.exception.AmbiguousInputException;


import java.util.HashMap;
import java.util.Map;

public enum Commands {
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
    private static final Map<String,Commands> aliasMap = new HashMap<>();

    static {
        for (Commands command : Commands.values()) {
            for (String alias : command.aliases) {
                aliasMap.put(alias,command);
            }
        }
    }

    Commands(String... aliases) {
        this.aliases = aliases;
    }

    public static Commands commandType(String alias) throws AmbiguousInputException {
        Commands command = aliasMap.get(alias);

        if (command == null) {
            throw new AmbiguousInputException();
        }

        return command;
    }
}
