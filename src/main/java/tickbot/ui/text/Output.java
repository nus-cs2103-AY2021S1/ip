package tickbot.ui.text;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The class to represent output in the text UI.
 */
public class Output {
    private Output() { } // not meant to be initialized

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

    /**
     * Display a message in text UI.
     * <p> A line break will be appended to the message. </p>
     */
    static void printMessage(String message) {
        System.out.println("  " + message);
    }

    /**
     * List all available commands in the text UI.
     */
    static void printAllUsage() {
        printMessage("All available commands:");
        for (Entry<String, String> entry : usages.entrySet()) {
            printMessage(entry.getValue());
        }
    }

    /**
     * Display the usage for a given command.
     * @param commandName the name of the command to look up the usage.
     */
    static void printUsage(String commandName) {
        String usageString = usages.get(commandName);
        if (usageString != null) {
            Output.printMessage(usageString);
        } else {
            Output.printMessage("Unknown command " + commandName + " .");
        }
    }
}