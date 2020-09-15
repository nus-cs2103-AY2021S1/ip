package duke;

/**
 * Encapsulates the duke.Ui and its behavior.
 */
public class Ui {
    private String message;

    Ui() {
        this.message = "";
    }

    void clearMessage() {
        this.message = "";
    }


    /**
     * Prints a welcome message for the user.
     */
    static String welcome() {
        return "Konichiwa! Welcome to Kaizen\n"
                + "I am Kai, what can I do for you today?\n";
    }

    String showError(Exception e) {
        return e.getMessage() + "\n";
    }

    void appendMessage(String str) {
        message += str;
    }

    @Override
    public String toString() {
        return message;
    }
}
