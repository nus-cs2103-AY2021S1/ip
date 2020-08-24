package duke.ui;

import java.util.HashMap;
import java.util.Map;

public enum Message {
    GREETING,
    EXIT_GREETING("Bye. Hope to see you again soon!"),
    DONE_MSG("Nice! I've marked this task as done"),
    DELETE_MSG("Noted. I've removed this task:"),
    CONFIRMATION_MSG("Got it. I've added this task:"),
    FETCHING_MSG("Here are the tasks in your list:"),
    ERROR_UNKNOWN_CMD("upset parser: unknown command"),
    ERROR_DONEDELETE_ARGS("invalid done/delete command: do things one at a time pls!"),
    ERROR_DONEDELETE_NOTINT("invalid done/delete command: you gotta pass an integer"),
    ERROR_TODO_DESC("invalid todo command: do what?"),
    ERROR_DEADLINE_FORMAT("invalid deadline command: bad format, try deadline <desc> /by <dateString>"),
    ERROR_DEADLINE_DESC("invalid deadline command: bro, where's the deadline description at?"),
    ERROR_EVENT_FORMAT("invalid event command: bad format, try event <desc> /at <dateString> <startTime>-<endTime>"),
    ERROR_EVENT_TIME("invalid event command: you need to pass in a start and end time for your event"),
    ERROR_EVENT_DATE("invalid event command: you didn't pass in the date!"),
    INVALID;
    
    
    private String msg;
    private static Map<String, Message> keyableMap;
    
    Message() {
    } // constructor for INVALID
    
    Message(String msg) {
        this.msg = msg;
    }
    
    // init for the enums class: fills in the hashmap of message-label pairs
    static {
        Map<String, Message> messageLabelMap = new HashMap<>();
        for (Message m : Message.values()) {
            messageLabelMap.put(m.getMsg(), m);
        }
        Message.keyableMap = messageLabelMap;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public static Message getLabel(String msg) {
        return keyableMap.getOrDefault(msg, Message.INVALID);
    }
    
    
}
