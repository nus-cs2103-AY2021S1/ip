package duke;

/**
 * UI class displays information to answer clients' queries.
 */
public class Ui {
    /**
     * Returns Duke welcoming message.
     *
     * @return String welcoming greeting.
     */
    public String showWelcome() {
        String greeting = "    ______________________________________\n";
        greeting += "        Hello! I'm Duke\n";
        greeting += "        What can I do for you?\n";
        greeting += "    ______________________________________\n";
        return greeting;
    }

    /**
     * Shows goodbye message.
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Shows invalid format command error message.
     */
    public String showInvalidFormatCommandDescription() {
        return "☹ OOPS!!! The description of a task cannot be empty or in wrong format\n";
    }

    /**
     * Shows meaningless command message.
     */
    public String showMeaninglessCommandDescription() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Prints out line background.
     */
    public String printLineBackground() {
        return "    _______________________________________________________________________________\n";
    }
}
