package duke.util;

/**
 * Responsible for displaying information to the user.
 */
public class Ui {
    private String line = "----------------------------------------------------------------";

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
     * Indents the given message by 1 tab and adds horizontal borders above and below it.
     * @param string The string to print in this format.
     */
    // Wrapper method for printing with horizontal line borders and 1 tab indent
    public void printResponse(String string) {
        String formatted = String.format("%s\n%s\n%s", line, string, line)
                .replaceAll("(?m)^", "\t");
        System.out.println(formatted);
    }

    /**
     * Prints a horizontal line.
     */
    private void showLine() {
        System.out.println(line);
    }
}
