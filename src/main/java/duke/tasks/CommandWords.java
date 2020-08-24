package duke.tasks;

import java.util.HashMap;
import java.util.Map;

public enum CommandWords {
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
    private static Map<String, CommandWords> keyableMap;
    
    CommandWords() {
    } // constructor for INVALID
    
    CommandWords(String cmd) {
        this.cmd = cmd;
    }
    
    // init for the enums class: fills in the hashmap of message-label pairs
    static {
        Map<String, CommandWords> commandLabelMap = new HashMap<>();
        for (CommandWords c : CommandWords.values()) {
            commandLabelMap.put(c.getCmd(), c);
        }
        CommandWords.keyableMap = commandLabelMap;
    }
    
    public String getCmd() {
        return this.cmd;
    }
    
    public static String getTag(CommandWords c) {
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
    
    public static CommandWords getLabel(String msg) {
        return keyableMap.getOrDefault(msg, CommandWords.INVALID);
    }
    
}
