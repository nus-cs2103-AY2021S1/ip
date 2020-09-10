import UI.UserInterface;
import javafx.application.Platform;

/**
 * Represents the main method of the MattBot program. The bot is able to track added todo tasks into the list and it
 * is stored locally in a save txt file called DukeTodoSave.txt located in the root of the project directory.
 * If this file is not present, the program will automatically create this text file in the root.
 */
public class Duke {

    static String getResponse(String input) {
        UserInterface UI = new UserInterface();
        UI.input(input);
        if (input.equals("bye")) {
            Platform.exit();
        }
        return UI.parse2();
    }

}
