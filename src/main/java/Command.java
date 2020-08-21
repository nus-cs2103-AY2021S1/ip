import java.util.HashMap;
import java.util.Map;

public enum Command {
    LIST_MODE("list"),
    ECHO_MODE("echo"),
    TODO_CMD("todo"),
    DEADLINE_CMD("deadline"),
    EVENT_CMD("event"),
    EXIT_CMD("bye"),
    LIST_CMD("list"),
    DONE_CMD("done"),
    DELETE_CMD("delete"),
    INVALID;
    
    
    private String cmd;
    private static Map<String, Command> keyableMap;
    
    Command() {
    } // constructor for INVALID
    
    Command(String cmd) {
        this.cmd = cmd;
    }
    
    // init for the enums class: fills in the hashmap of message-label pairs
    static {
        Map<String, Command> commandLabelMap = new HashMap<>();
        for (Command c : Command.values()) {
            commandLabelMap.put(c.getCmd(), c);
        }
        Command.keyableMap = commandLabelMap;
    }
    
    public String getCmd() {
        return this.cmd;
    }
    
    public static String getTag(Command c) {
        switch (c.getCmd().charAt(0)) {
        case 'e':
            return "E";
        case 'd':
            if (c.getCmd().equals("delete")) {
                return "D";
            } else {
                return INVALID.cmd;
            }
        case 't':
            return "T";
        default:
            return INVALID.cmd;
        }
        
        
    }
    
    public static Command getLabel(String msg) {
        return keyableMap.getOrDefault(msg, Command.INVALID);
    }
    
}
