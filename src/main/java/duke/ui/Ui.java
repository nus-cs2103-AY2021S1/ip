package duke.ui;

public class Ui {

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints message that greets user when Dino starts up.
     */
    public void greet() {
        String logo = " ____\n"
                + "|  _ \\ _ _____  ___\n"
                + "| | | | |  _  |/   \\\n"
                + "| |_| | | | | | |_| |\n"
                + "|____/|_|_| |_|\\___/\n";
        System.out.println("Rawr! I'm Dino ><\n"
                +logo
                + "\nGet started on your task list by entering a task!"
                + "\nTo see how to format your task, input 'format'"
                + "\nTo see your list of tasks, input 'list'."
                + "\nTo mark a task as done, input 'done <task number>'."
                + "\nTo delete a task from your list, input 'delete <task number>'."
                + "\nTo stop Dino, input 'bye'."
                + "\n____________________________________________________________");
    }

    /**
     * Prints closing message for user when Dino exits.
     */
    public void bye() {
        // Dino says bye
        System.out.println("Rawr. Hope to see you again soon! ><");
    }

    /**
     * Prints formatting information for user to refer
     * when inputting command for Dino to execute.
     */
    public void showFormat() {
        System.out.println("Formats for the three task types Todo, Deadline and Event,"
                + " are shown below.\n"
                + "Todo: 'todo <task description>'"
                + " (e.g. todo visit new theme park)\n"
                + "Deadline: 'deadline <task description>"
                + " /by <yyyy-mm-dd hhmm>' (e.g. deadline submit report /by 2019-11-10 1500)\n"
                + "Event: 'event <task description>"
                + " /at <yyyy-mm-dd hhmm-hhmm>' "
                + "(e.g. event team project meeting /at 2019-10-02 1400-1500)\n"
                + "\nAdditional Information:"
                + "\nTo mark a task as done, input 'done <task number>'."
                + "\nTo delete a task from your list, input 'delete <task number>'.");
    }

    /**
     * Prints given error message to tell user error has occurred.
     *
     * @param message String of error message
     */
    public void showError(String message) {
        System.out.println(message);
    }


    /**
     * Prints given success message to tell user
     * that command has been successfully executed.
     *
     * @param message String of success message
     */
    public void showSuccess(String message) {
        System.out.println(message);
    }

}
