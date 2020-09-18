package Duke;

import Duke.UI.UI;
import Duke.commands.Parser;

public class Duke {

    /**
     * Gets a response from Duke given the
     * @param input
     * @return
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bark bark! (Goodbye!)";
        }
        try {
            return Parser.parseAndAddToList(input);
        } catch (DukeExceptions e) {
            return e.getMessage();
        }
    }

    public static String getGreeting() {
        return UI.getMessage("WELCOME_MSG") + "\n";
    }
}