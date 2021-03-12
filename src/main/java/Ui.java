/**
 * Represents a Ui object.
 * Prints hardcoded designs that prompts the user.
 */
public class Ui {

    /**
     * Displays the welcome greetings when user runs Duke program.
     */
    public static String welcomeGreetings() {
        return "***Welcome to Project DUKE***  \n"
                + "CS2103T Individual Project  \n"
                + "\nHello! I'm Duke  "
                + "\nWhat can I do for you?  ";
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
                + "\nbye, list, find, done, delete, todo, event, deadline"
                + "\n***Don't be afraid to key in wrong commands! I will prompt you :)";
    }
}
