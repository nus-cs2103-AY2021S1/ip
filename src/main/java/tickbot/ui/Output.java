package tickbot.ui;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The class to represent output in the text UI.
 */
public class Output {
    private static PrintStream printStream = System.out;
    private static String prefix = "  ";

    private static Map<String, String> usages = new HashMap<>() {
        private static final long serialVersionUID = 1L;
        {
            put("help", "help <command>");
            put("bye", "bye");
            put("done", "done <index>");
            put("delete", "delete <index>");
            put("list", "list");
            put("todo", "todo <content>");
            put("deadline", "deadline <content> /by <YYYY-MM-DD>");
            put("event", "event <content> /at <YYYY-MM-DD>");
        }
    };

    private Output() { } // not meant to be initialized

    /**
     * Set the printing stream used by this class.
     * <p>{@code System.out} will be used if not set.</p>
     * @param stream The new printing stream.
     */
    public static void setPrintStream(PrintStream stream) {
        Output.printStream = stream;
    }

    /**
     * Set the prefix string while printing out a message.
     * <p>Two spaces are used if not set.</p>
     * @param prefix the new prefix string.
     */
    public static void setPrefix(String prefix) {
        Output.prefix = prefix;
    }

    /**
     * Display a message in text UI.
     * <p>A line break will be appended to the message.</p>
     */
    public static void printMessage(String message) {
        printStream.println(prefix + message);
    }

    /**
     * List all available commands in the text UI.
     */
    public static void printAllUsage() {
        printMessage("All available commands:");
        for (Entry<String, String> entry : usages.entrySet()) {
            printMessage(entry.getValue());
        }
    }

    /**
     * Display the usage for a given command.
     * @param commandName the name of the command to look up the usage.
     */
    public static void printUsage(String commandName) {
        String usageString = usages.get(commandName);
        if (usageString != null) {
            Output.printMessage(usageString);
        } else {
            Output.printMessage("Unknown command " + commandName + " .");
        }
    }
}
