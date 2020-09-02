package dude.ui;

/**
 * DialogWrapper class wraps the ui to be shown with the exit status.
 */
public class DialogWrapper {
    private final String message;
    private final boolean isExit;

    /**
     * Constructor for the DialogWrapper class.
     * @param message message for the user.
     * @param isExit is the program exiting.
     */
    public DialogWrapper(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    protected String getMessage() {
        return message;
    }

    protected boolean getExitStatus() {
        return isExit;
    }
}
