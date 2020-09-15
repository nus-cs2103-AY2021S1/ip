package util;

/**
 * Deals with interactions with the user
 * */
public class Ui {
    /** Constants **/
    private final String outputSymbol = ">>> ";
    private final String lineBreak = "--.--.--.--.--.--.--.--.--.--.--."
            + "--.--.--.--.--.--.--.--.--.--.--";
    // Text Images
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Other variable
    private String greetings = "";

    /**
     * Constructors.
     */
    public Ui() {
    }

    /**
     * Sets Duke's load message as a string.
     */
    public void setGreetings(String loadMessage) {
        this.greetings = loadMessage + "\n";
    }

    /**
     * Returns Duke's greetings as a string.
     *
     * @return Duke's greetings as a String
     */
    public String getGreetings() {
        return greetings
                + "\nHello, I'm Duke! "
                + "What can I help you with today?"
                + "\n" + lineBreak + "\n";
    }

    /**
     * Gets Duke's farewell to user.
     *
     * @return Duke's farewell as a String
     */
    public String getFarewells() {
        return outputSymbol
                + "Bye! Hoped I helped!"
                + "\n" + lineBreak;
    }

    /**
     * Returns a line break and Duke's symbols before response.
     *
     * @return The string containing output symbols.
     */
    public String getOutputSymbol() {
        return lineBreak + "\n" + outputSymbol;
    }

    /**
     * Returns the line break string.
     *
     * @returns Line break as String.
     */
    public String getLineBreak() {
        return lineBreak + "\n";
    }

    /**
     * Prints error messages from DukeException.
     *
     * @param e The DukeException to print message of.
     * @return String message of exception.
     */
    public String getError(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Returns all tasks within a TaskList (if any).
     * Else prints a list is empty message.
     *
     * @param list TaskList of Duke.
     * @return List of tasks in String.
     */
    public String getList(TaskList list) {
        String output = "Here is what I have! ^^\n";
        if (list.isListEmpty()) {
            // Handles printing empty list
            output += "Whoops! I don't have anything of note yet...\n";
        } else {
            output += list.getAllTasks();
        }
        return output;
    }
}
