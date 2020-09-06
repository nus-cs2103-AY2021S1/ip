package duke;

import duke.dependencies.parser.Controller;


/**
 * Class that separates the command checker/parser from the main(). Catches command that
 * ends/closes program, terminating it immediately. Prints to std::out.
 *
 */
class Ui {
//    private static final String DIVIDER = "____________________________________________________________\n";
//    private static final String LOGO = " ____        _        \n"
//                                    + "|  _ \\ _   _| | _____ \n"
//                                    + "| | | | | | | |/ / _ \\\n"
//                                    + "| |_| | |_| |   <  __/\n"
//                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello, I'm Duke\nwhat can I do for you?\n";

    private static final String CIAO ="Spero di rivederti presto\n";
    private static final String CONVO_START = GREETING;
    private static final String END = "end|ciao|bye|close|exit|nights|shutdown";

    private static final Controller CONTROLLER = Controller.init();

    private boolean enterPasswordMode = false;
    private boolean confirmPasswordMode = false;
    private String password;


    /**
     * Starts the Duke application.
     */
    public void start() {
        System.out.println(CONVO_START);
    }

    /**
     * Stops the Duke application.
     */
    public void end() {
        System.out.println(CIAO);
        System.exit(0);
    }

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
        if (!s.isEmpty() && END.contains(s)) {
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
        if (s.length() == 2 && s.contains("hi")) {
            return CONVO_START;
        }

        String reply = CONTROLLER.parseAndExec(s);

        if (reply.equals("Error")) {            // Error. However this should not occur
//            System.out.println(DIVIDER + reply + "\n" + DIVIDER);
            return reply;
        } else {
            // Normal execution
//            System.out.println(DIVIDER + reply + "\n" + DIVIDER);
            return reply;
        }
    }
}
