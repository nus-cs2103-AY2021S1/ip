package godfather.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * An Enumeration of the various command words that shall be use on Duke
 */
public enum CommandWord {
    ECHO_MODE("echo", "echo help message"),
    TODO_CMD("todo", "A todo keeps track of what you need to do.\n\t"
            + "\u2764 type \"todo <description>\" to create one\n"
            + "here's a valid example:\n"
            + "\"todo make an offer you can't refuse\""),
    DEADLINE_CMD("deadline",
                 "Deadlines keep track of when you need to get things done by.\n" + "The syntax for it is:\n"
                         + "\u2764 \"deadline <description of the deadline> /by <date & time string>\"\n"
                         + "\u2764 <date & time string> is <date> <time> separated by a space character\n"
                         + "\u2764 <date> can be in any of the following formats: d/M/yyyy, yyyy/M/d\n"
                         + "\u2764 <time> can be written as HHmm in 24h time\n"
                         + "here's a valid example:\n"
                         + "\"deadline make an offer you can't refuse /by 2020/12/2 2359\""),
    EVENT_CMD("event", "Events keep track of when you have events.\n"
            + "The syntax for it is:\n"
            + "\u2764 \"event <description of the event> /at <date & timeInfo string>\"\n"
            + "\u2764 <date & time string> is <date> <timeInfo> separated by a space character\n"
            + "\u2764 <date> is just a string representation for a date\n"
            + "\u2764 <timeInfo> is <startTime>-<endTime> separated by "
            + "a \"-\" character\n here's a valid example:\n \t\"event durian party /at Mon 2-4pm\""),
    EXIT_CMD("bye",
             "Just type \"bye\" and you'll be able to quit the application.\n"
                     + "Your secrets are always safe with the family no matter what you do,"
                     + " you'll never lose your data\u2726"),
    LIST_CMD("list", "Just type \"list\" and you'll get a list of all your events fetched to you"),
    DONE_CMD("done", "Godfather shall mark an event as done,\n\u2764 type \"done <task ID>\" to mark "
            + "a task as done\nif you need to know what task ID it is, try listing out using the list command"),
    DELETE_CMD("delete",
               "To delete an event,\n\u2764 type \"delete <task ID>\" to remove it forever\n"
                       + " if you need to know what task ID it is, try listing out using the list command"),
    FIND_CMD("find", "type \"find <pattern>\" for Godfather to fetch tasks that have the exact pattern "
            + "as a substring"),
    HELP_CMD("help",
             "type \"help <topic>\" to get more info about a topic. "
                     + "Available topics include:\n"
                     + "\t\u2726 todo       \u2726 deadline\n"
                     + "\t\u2726 event     \u2726 bye\n"
                     + "\t\u2726 list         \u2726 done\n"
                     + "\t\u2726 find        \u2726 delete"
                     + "\n here's a valid example:\n \t\"help deadline\""),
    INVALID("invalid", "invalid help msg");
    private static Map<String, CommandWord> keyableMap;
    private final String cmd;
    private final String helpMsg;
    CommandWord(String cmd, String helpMsg) {
        this.cmd = cmd;
        this.helpMsg = helpMsg;
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
    /* Returns the string representation for that command
     *  @return Command's String form
     */
    public static CommandWord getCommandWordFromString(String input) {
        return CommandWord.keyableMap.get(input);
    }
    public String getCmd() {
        return this.cmd;
    }
    public String getHelpMsg() {
        return this.helpMsg;
    };
}
