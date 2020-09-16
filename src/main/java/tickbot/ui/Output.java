package tickbot.ui;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The class to represent output in the text UI.
 */
public class Output {
    private static final String TIME_FORMAT = "<yyyy-MM-dd[ HH:mm[:ss]]>";
    private static PrintStream printStream = System.out;

    private static Map<String, String> usages = new HashMap<>() {
        private static final long serialVersionUID = 1L;
        {
            put("help", "help <command>");
            put("bye", "bye");
            put("done", "done <index>");
            put("delete", "delete <index>");
            put("list", "list");
            put("todo", "todo <content>");
            put("deadline", "deadline <content> /by " + TIME_FORMAT);
            put("event", "event <content> /at " + TIME_FORMAT);
            put("tag", "tag <index> [<tag> ...]");
        }
    };

    private Output() { } // not meant to be initialized

    /**
     * Sets the printing stream used by this class.
     * <p>{@code System.out} will be used if not set.</p>
     * @param stream The new printing stream.
     */
    public static void setPrintStream(PrintStream stream) {
        Output.printStream = stream;
    }

    /**
     * Displays a message in text UI.
     * <p>A line break will be appended to the message.</p>
     */
    public static void printMessage(String message) {
        printStream.println(message);
    }

    /**
     * Lists all available commands in the text UI.
     */
    public static void printAllUsage() {
        printMessage("All available commands:");
        for (Entry<String, String> entry : usages.entrySet()) {
            printMessage(entry.getValue());
        }
    }

    /**
     * Displays the usage for a given command.
     * @param commandName The name of the command to look up the usage.
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
