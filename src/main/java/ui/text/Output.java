package ui.text;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

    static void printMessage(String message) {
        System.out.println("  " + message);
    }

    static void printAllUsage() {
        printMessage("All available commands:");
        for (Entry<String, String> entry : usages.entrySet()) {
            printMessage(entry.getValue());
        }
    }

    static void printUsage(String commandName) {
        String usageString = usages.get(commandName);
        if (usageString != null) {
            Output.printMessage(usageString);
        } else {
            Output.printMessage("Unknown command " + commandName + " .");
        }
    }
}