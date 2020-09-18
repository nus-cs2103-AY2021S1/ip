/**
 * Represents a Ui object.
 * Prints hardcoded designs that prompts the user.
 */
public class Ui {

    /**
     * Displays the welcome greetings when user runs Duke program.
     */
    public static String welcomeGreetings() {
        return "    _______________________________________________________________________\n"
                + "     ***Welcome to Project DUKE***\n"
                + "     CS2103T Individual Project\n"
                + "\n     Hello! I'm Duke"
                + "\n     What can I do for you?"
                + "\n    _______________________________________________________________________\n";
    }

    /**
     * Displays the error message when there is error updating the file.
     *
     * @param message is the essence of error.
     *
     * @return a file update error message.
     */
    public String displayUpdateFileError(String message) {
        return "*Error: Unable to save changes to file*"
                + "\n  Please check if your file still exists in your directory"
                + "\n" + message;
    }

    /**
     * Displays the Commands in GUI when "Commands" button is pressed.
     *
     * @return a Command list message.
     */
    public static String getCommandList() {
        return "***Duke Commands: "
                + "\n    bye, list, find, done, delete, todo, event, deadline\n"
                + "\nDon't be afraid to key in wrong commands! I will prompt you :)";
    }
}
