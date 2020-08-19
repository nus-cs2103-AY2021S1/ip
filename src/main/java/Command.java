import java.util.HashMap;
import java.util.Map;

public enum Command {
    ADD_TODO        ("todo"),
    ADD_DEADLINE    ("deadline"),
    ADD_EVENT       ("event"),
    LIST            ("list"),
    DELETE          ("delete"),
    MARK_AS_DONE    ("done"),
    EXIT            ("bye");

    private final String shortcut;

    private static final Map<String, Command> COMMAND_STRING_MAP = new HashMap<>();

    // Cache the command and its corresponding shortcut for reverse lookup
    static {
        for (Command command : values()) {
            COMMAND_STRING_MAP.put(command.shortcut, command);
        }
    }

    Command(String shortcut) {
        this.shortcut = shortcut;
    }

    public static Command getCommand(String shortcut) {
        return COMMAND_STRING_MAP.get(shortcut);
    }
}
