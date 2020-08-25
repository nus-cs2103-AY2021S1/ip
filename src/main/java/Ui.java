/**
 * Represents a Ui object.
 * Prints hardcoded designs that prompts the user.
 */
public class Ui {
    private static String TITLE = "     CS2103T Individual Project 1\n";

    /**
     * Displays the welcome greetings when user runs Duke program.
     */
    public static void displayWelcome() {
        System.out.println("    _______________________________________________________________________\n"
                + "     ***Welcome to Project DUKE***\n"
                + TITLE
                + "\n     Hello! I'm Duke"
                + "\n     What can I do for you?"
                + "\n    _______________________________________________________________________\n");
    }

    /**
     * Displays the error message when there is error loading the file.
     */
    public static void displayLoadFileError() {
        System.out.println("    _______________________________________________________________________"
                + "\n       *Error: File not found*"
                + "\n     Please specify the correct file location relative to your project"
                + "\n     root directory"
                + "\n    _______________________________________________________________________\n");
    }

    /**
     * Displays the message when file is loaded successfully.
     */
    public static void displayLoadFileSuccess() {
        System.out.println("    _______________________________________________________________________"
                + "\n       File has successfully loaded!"
                + "\n     Duke Commands: bye, list, todo, event, deadline, delete"
                + "\n    _______________________________________________________________________\n");
    }

    /**
     * Displays the message when user inputs an unrecognisable command.
     *
     * @param message is the essence of error.
     */
    public static void displayUserInputError(String message) {
        System.out.println("       " + message
                + "\n    _______________________________________________________________________\n");
    }

    /**
     * Displays the error message when there is error updating the file.
     *
     * @param message is the essence of error.
     */
    public static void displayUpdateFileError(String message) {
        System.out.println("    _______________________________________________________________________"
                + "\n       *Error: Unable to save changes to file*"
                + "\n     Please check if your file still exists in your directory"
                + "\n     " + message
                + "\n    _______________________________________________________________________\n");
    }
}
