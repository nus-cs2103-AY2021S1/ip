import java.util.HashMap;
import java.util.Map;

public enum Command {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    LIST("list"),
    TODO("todo");

    private String value;

    private Command(String value) {
        this.value = value;
    }

    private static Map<String, Command> commandsMap;

    static {
        commandsMap = new HashMap<>();
        for(Command command : Command.values()) {
            commandsMap.put(command.value, command);
        }
    }

    public static Command getCommand(String command) {
        return Command.commandsMap.get(command);
    }

    @Override
    public String toString() {
        return this.value;
    }

}
