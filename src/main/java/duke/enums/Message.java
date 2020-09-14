package duke.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An Enumeration of the various Messages words that shall be used by Duke's UI
 */
public enum Message {
    GREETING("Tell me,\nWhat can I do for you? \n\t if you need inspiration, type help"),
    EXIT_GREETING("Grazie, we will meet again!"),
    DONE_MSG("I am proud that you did what you had to do"),
    DELETE_MSG("Understood. The task will no longer be a problem:"),
    CONFIRMATION_MSG("I will see that you get this done:"),
    FETCHING_MSG("Here's what you have to do:"),
    FOUND_MSG("These are your matching tasks:"),
    ERROR_NO_DATE_FORMATTER("Can't find Date Formatter"),
    ERROR_NO_TIME_FORMATTER("Can't find Time Formatter"),
    ERROR_BAD_TIME_INPUT("Disrespectful! What kinda date/time is that"),
    ERROR_NO_FIND("Your search term... I don't remember anything like it"),
    ERROR_UNKNOWN_CMD("You leave me no choice, I don't understand you"),
    ERROR_DONEDELETE_ARGS("Didn't your mother teach you to do things one at a time? invalid done/delete command"),
    ERROR_DONEDELETE_NOTINT("Didn't you mother teach you to pass in integers? invalid done/delete command"),
    ERROR_TODO_DESC("What is it you want to do? invalid todo command"),
    ERROR_DEADLINE_FORMAT("invalid deadline command: bad format, try deadline <desc> /by <dateString>"),
    ERROR_DEADLINE_DESC("invalid deadline command: bro, where's the deadline description at?"),
    ERROR_EVENT_FORMAT("invalid event command: bad format, try event <desc> /at <dateString> <startTime>-<endTime>"),
    ERROR_EVENT_TIME("invalid event command: you need to pass in a start and end time for your event"),
    ERROR_EVENT_DATE("invalid event command: you didn't pass in the date!"),
    HELP_MSG("\n\tfor help on a specific topic, type help <topic>. \nTopics include: List, Todo, Deadline, Event,"
                    + " Deadline, Done, Delete"),
    INVALID;
    private static Map<String, Message> keyableMap;
    private String msg;
    Message() {
    } // constructor for INVALID
    Message(String msg) {
        this.msg = msg;
    }
    /*
     * An Initialisation for the Hashmap that allows us to access keys from their values (message-label pairs)
     */
    static {
        Map<String, Message> messageLabelMap = new HashMap<>();
        for (Message m : Message.values()) {
            messageLabelMap.put(m.getMsg(), m);
        }
        Message.keyableMap = messageLabelMap;
    }
    /**
     * Returns the string representation for that Message
     *
     * @return Message's String form
     */
    public String getMsg() {
        return this.msg;
    }
}
