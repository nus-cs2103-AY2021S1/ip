package duke.enums;

import java.util.HashMap;
import java.util.Map;

public enum CommandWord {
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
    private static Map<String, CommandWord> keyableMap;
    
    CommandWord() {
    } // constructor for INVALID
    
    CommandWord(String cmd) {
        this.cmd = cmd;
    }
    
    // init for the enums class: fills in the hashmap of message-label pairs
    static {
        Map<String, CommandWord> commandLabelMap = new HashMap<>();
        for (CommandWord c : CommandWord.values()) {
            commandLabelMap.put(c.getCmd(), c);
        }
        CommandWord.keyableMap = commandLabelMap;
    }
    
    public String getCmd() {
        return this.cmd;
    }
    
    public static CommandWord getLabel(String msg) {
        return keyableMap.getOrDefault(msg, CommandWord.INVALID);
    }
    
}
