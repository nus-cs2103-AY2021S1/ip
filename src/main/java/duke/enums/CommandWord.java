package duke.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An Enumeration of the various command words that shall be use on Duke
 */
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
    
    /*
     * An Initialisation for the Hashmap that allows us to access keys from their values (command-string pairs)
     */
    static {
        Map<String, CommandWord> commandLabelMap = new HashMap<>();
        for (CommandWord c : CommandWord.values()) {
            commandLabelMap.put(c.getCmd(), c);
        }
        CommandWord.keyableMap = commandLabelMap;
    }
    
    /**
     * Returns the string representation for that command
     *
     * @return Command's String form
     */
    public String getCmd() {
        return this.cmd;
    }
}
