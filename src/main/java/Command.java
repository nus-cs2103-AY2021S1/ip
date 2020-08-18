import java.util.HashMap;
import java.util.Map;

public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;
    private static Map<String, Command> map;

    private Command(String command) {
        this.command = command;
    }

    static {
        map = new HashMap<>();
        for(Command c : Command.values()) {
            map.put(c.command, c);
        }
    }

    public static Command getValue(String s) {
        return Command.map.get(s);
    }
}
