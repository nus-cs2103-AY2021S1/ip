package logic;

/**
 * Handles what the user will be seeing when running logic.Duke.
 */
public class Ui {

    public Ui() {}

    /**
     * Show user the greeting message.
     */
    public String showGreeting() {
        String result = "";
        result += "\t________________________________________________\n";
        result += "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        result += "\t________________________________________________\n";
        return result;
    }

    /**
     * Show error message thrown.
     *
     * @param errorMsg Error message of thrown error.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Print the message according to the input given.
     *
     * @param input Output for the user to read.
     */
    public String printOutput(String input) {
        return input;
    }
}
