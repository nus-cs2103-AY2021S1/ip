package duke.util;

/**
 * Responsible for displaying information to the user.
 */
public class Ui {
    /**
     * Prints out a greeting.
     */
    public void greet() {
        String greeting = "Hello! I'm Duke. \nWhat can I do for you?";
        printResponse(greeting);
    }

    /**
     * Prints out an loading error message.
     */
    public void showLoadingError() {
        printResponse("Error loading file");
    }

    /**
     * Prints out the error message of an error.
     *
     * @param e The error that occurred.
     */
    public void showError(Exception e) {
        printResponse(e.getMessage());
    }

    /**
     * Prints a given string with indentation and horizontal lines at the top and bottom.
     *
     * @param string The string to print in this format.
     */
    public void printResponse(String string) {
        String line = "----------------------------------------------------------------";
        String response = String.format("%s\n%s\n%s", line, string, line)
                .replaceAll("(?m)^", "\t");
        System.out.println(response);
    }
}
