package raythx98.grandma.ui;

/**
 * Deals with the user interface.
 */
public class Ui {

    private String textOnScreen = "";

    /**
     * Returns a greeting message.
     *
     * @return greeting message.
     */
    public String greetingMessage() {
        return "Hello mah dud, itza ol' grandma robo speakin :D "
                + appendHelpMessage()
                + "\nwhat duh hell du yu wan?";
    }

    /**
     * appends a farewell message to textOnScreen.
     */
    public void appendFarewellMessage() {
        textOnScreen += "Never come back,\n"
                + "dun wanna see yu ever agin.\n"
                + "Exiting in 5... \n\nminutes? hours? idk you'll find out \nlul GTFO";
    }

    /**
     * append and return a help message to textOnScreen.
     *
     * @return help message.
     */
    public String appendHelpMessage() {
        textOnScreen += "These r wud u tell your ol' grandma here...\n"
                + "Yu wan add more tasks\n        todo {description} \n                /by {YYYY-MM-DD HHmm}\n"
                + "        deadline {description} \n                /by {YYYY-MM-DD HHmm}\n"
                + "        event {description} \n                /at {YYYY-MM-DD HHmm}\n"
                + "Yu wan remove tasks coz incompetent\n        delete {task number}\n"
                + "Yu wan mark task as completed\n        done {task number}\n"
                + "Yu wan see ur tasks coz u forget\n        list\n"
                + "Yu wan find ur tasks coz u noob\n        find {keyword}\n"
                + "Yu wanna gtfo\n        bye";
        return textOnScreen;
    }

    /**
     * reset textOnScreen to an empty String.
     */
    public void resetTextOnScreen() {
        textOnScreen = "";
    }

    /**
     * Appends a string to textOnScreen.
     *
     * @param message the string to be appended.
     */
    public void appendMessage(String message) {
        textOnScreen += message;
    }

    /**
     * Return textOnScreen.
     *
     * @return String textOnScreen.
     */
    public String getTextOnScreen() {
        return textOnScreen;
    }

    /**
     * Returns the error message.
     *
     * @param exception the Exception to be handled.
     * @return the error message.
     */
    public String getError(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Return an error message.
     *
     * @return Error message.
     */
    public String getUncheckedException() {
        return "Wtf kind of error u giving me";
    }
}
