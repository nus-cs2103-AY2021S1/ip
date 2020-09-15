package duke;

import duke.dependencies.longtext.HelpText;
import duke.dependencies.longtext.StoryText;
import duke.dependencies.parser.Controller;


/**
 * Class that separates the command checker/parser from the main(). Catches command that
 * ends/closes program, terminating it immediately. Prints to std::out.
 *
 */
class Ui {

    private static final String GREETING = "Hello, I'm pipboy\nWelcome " +
            "to year 2287, you have been away for some time now "
            + "time to get yourself oriented to this place....\n";

    private static final String CIAO ="Spero di rivederti presto\n";
    private static final String CONVO_START = GREETING;
    private static final String END = "|end|ciao|bye|close|exit|nights|shutdown|";
    private static final String USER_GREETINGS = "|hi|hey|wassup|";

    private static final Controller CONTROLLER = Controller.init();

    private boolean enterPasswordMode = false;
    private boolean confirmPasswordMode = false;
    private boolean hasBeenGreeted = false;
    private String password;


    /**
     * Receives command from user.
     * Handling of "bye" command takes place here, for now.
     * This method is the main display method of Duke, and prints straight to std::out.
     *
     * @param s command given by user
     * @return -1 indicating failure, 0 indicating end of program, 1 indicating program is running
     */
    public String takeInputAndReturn(String s) {
        // End command
        if (!s.isEmpty() && END.contains("|" + s + "|")) {
            return "See yall around!!!";
        }
        // Checks if the second entering of the password matches the first entering.
        if (confirmPasswordMode) {
            // The second entering of password matches the first entering.
            if (password.equals(s)) {
                CONTROLLER.saveUserDetails(password);
                confirmPasswordMode = false;
                enterPasswordMode = false;
                return "Password saved successfully.";
            } else {
                enterPasswordMode = false;
                confirmPasswordMode = false;
                return "The password you have entered does not match.";
            }
        }
        // First attempt of entering the password by the user.
        if (enterPasswordMode) {
            password = s;
            confirmPasswordMode = true;
            enterPasswordMode = false;
            return "Please enter your password again.";
        }
        if (!CONTROLLER.hasUserEnteredDetails()) {
            enterPasswordMode = true;
            return "You are somebody new! Please state your user password.";
        }
        // Initiating a conversation with duke with 'hi'
        if (!hasBeenGreeted && USER_GREETINGS.contains("|" + s + "|")) {
            hasBeenGreeted = true;
            return CONVO_START + "\n" + StoryText.STORY_OF_FALLOUT;
        }

        if (s.equals("help")) {
            return HelpText.HELP_TEXT;
        }

        if (hasBeenGreeted && USER_GREETINGS.contains("|" + s + "|")) {
            return "Why are you greeting me again?";
        }

        String reply = CONTROLLER.parseAndExec(s);

        return reply;
    }
}
