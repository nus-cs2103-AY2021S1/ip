package Duke;

import Duke.UI.UI;
import Duke.commands.Parser;

public class Duke {

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
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