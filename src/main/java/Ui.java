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
     * Displays the error message when there is error loading the file.
     */
    public static String loadFileErrorMessage() {
        return "*Error: Text File not found*"
                + "\nPlease specify the correct file location"
                + "\nto read and write on the text file.";
    }

    /**
     * Displays the message when file is loaded successfully.
     */
    public static String loadFileSuccessMessage() {
        return "*File has successfully loaded!*\n"
                + "\nDuke Commands: "
                + "\nbye, list, find, done, delete, todo, event, deadline";
    }

    /**
     * Displays the error message when there is error updating the file.
     *
     * @param message is the essence of error.
     */
    public String displayUpdateFileError(String message) {
        return "*Error: Unable to save changes to file*"
                + "\n  Please check if your file still exists in your directory"
                + "\n" + message;
    }
}
