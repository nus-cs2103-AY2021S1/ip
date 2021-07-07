package seedu.duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Main class that interacts with user inputs.
 */
public class Ui {
    private static final String BYE = "Bye! Hope to see you again soon.\n";
    private Parser parse;
    private boolean isAlive = true;

    /**
     * Initializes an instance of Ui.
     *
     * @param parser Parser to be part of Ui.
     */
    public Ui(Parser parser) {
        parse = parser;
    }

    /**
     * Gets userInput from GUI users.
     *
     * @param input string input from GUI.
     * @return String output by DUI on GUI.
     */
    public String getUserInput(String input) {
        if (input.strip().equals(Keyword.BYE.label)) {
            return Ui.endDukeGui();
        }
        return parse.readGuiInput(input);
    }

    /**
     * Gets status of Ui.
     *
     * @return false if "bye" command is input by user. Otherwise, true.
     */
    public boolean checkDukeStatus() {
        return isAlive;
    }

    /**
     * Sets boolean isAlive to false. s duke program.
     */
    public void discontinue() {
        isAlive = false;
    }

    /**
     * Static method to end Duke GUI when user input "Bye".
     */
    public static String endDukeGui() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
                System.exit(0);
            }
        }, 3000);
        return BYE;
    }
}
