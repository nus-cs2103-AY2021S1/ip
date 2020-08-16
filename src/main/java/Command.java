import java.util.HashMap;
import java.util.Map;

public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    UNKNOWN("unknown");

    private final String commandName;
    private static Map<String, Command> commandMap;

    Command(String commandName) {
        this.commandName = commandName;
    }

    static {
        final Map<String, Command> strToCommand = new HashMap<>();
        for (Command c : Command.values()) {
            strToCommand.put(c.toString(), c);
        }
        Command.commandMap = strToCommand;
    }

    public static Command getCommand(String command) {
        if(!commandMap.containsKey(command)) {
            return Command.UNKNOWN;
        } else {
            return commandMap.get(command);
        }
    }

    @Override
    public String toString() {
        return this.commandName;
    }
}
