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
        result += "________________________________________________\n";
        result += "Hello! I'm Duke\nWhat can I do for you?\n\n"
                + "These are the commands I support:\n"
                + "1. list => to show all existing tasks\n"
                + "2. todo ..... => to add a To-Do task to the list\n"
                + "3. event ..... => to add an Event task to the list\n"
                + "4. deadline ..... => to add a Deadline task to the list\n"
                + "5. done x => to complete task #x\n"
                + "6. delete y => to delete task #y\n"
                + "7. exit => to save the tasks and exit Duke\n";
        result += "________________________________________________\n";
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
