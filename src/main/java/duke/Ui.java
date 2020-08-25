package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * User interface which deals with user interactions.
 * Also prints relevant error messages and responses.
 */
public class Ui {
    static final String SOCCAT =
            "                 .                         .                             \n" +
                    "                ...                       ...                            \n" +
                    "               /@@@&*                   (@@@@@&*                         \n" +
                    "              /@@@@@@@@&*            /@@@@@@@@@@#                        \n" +
                    "             *&@@@@@@@@@@@@*       /@@@@@@@@@@@@&/                       \n" +
                    "             %&@@@@@@@@@@@@@@%.  *@@@@@@@@@@@@@@@&/                      \n" +
                    "            /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                     \n" +
                    "           /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                   \n" +
                    "         .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
                    "        *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.               \n" +
                    "       #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,              \n" +
                    "      %@@@@@@@@@@@@*,#@@@@@@@@@@@@@@@@@@@%..,&@@@@@@@@@@@@@/             \n" +
                    "     (@@@@@@@@@@@%    *@@@@@@@@@@@@@@@@@@    ,@@@@@@@@@@@@@&             \n" +
                    "     @@@@@@@@@@@@@@@   &@@@@@@@@@@@@@@@@@@&.  %@@@@@@@@@@@@@(            \n" +
                    "    (@@@@@@@@@@@@(     #@@@@@@@@@@@@@@@@@.    /@@@@@@@@@@@@&%            \n" +
                    "    *@&&&&&@@@@@@(     /@@@@@@@@@@@@@@@@@.    *@@@@@@&&&&&&&(            \n" +
                    "     %&&&&&&&&@@@&     (@@@@@@@@@@@@@@@@@*    #@@@&@&&&&&&&%             \n" +
                    "     .&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&,             \n" +
                    "       ,&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&(               \n" +
                    "         .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&/                 \n" +
                    "             ,#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%*.                    \n" +
                    "                    ,/#@@@@@@@@@@@@@@@@@@@@@@#                           \n" +
                    "                      #&@@@@@@@@@@@@@@@@@@@&&&&.                         \n" +
                    "                     ,&@@@@@@@@@@@@@@@@@@@@@@@@@/                        \n" +
                    "                     #@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                      \n" +
                    "                    ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                     \n" +
                    "                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&.                   \n" +
                    "                   /@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@&,                  \n" +
                    "                   %@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@&.                 \n" +
                    "                   @@@@@@@@@@@@@@@@@@@@@%&@@@@@@@@@@@@&&#*,.             \n" +
                    "                  *@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@&&*.,#&&@@@*        \n" +
                    "                  ,@@@@@@@@@&@@@@@@@@@@&@@@@@@@@@@@@&&(       .(&@@(     \n" +
                    "                    ./#%&&%..,***////**(@@@@@@@@@&&%*            ,@@@*   \n" +
                    "                                                                    @@(  \n" +
                    "                                                       *%#*@#/@#(&@&.    \n" +
                    "                                                  /&@@@%##/&@@@@&,       \n" +
                    "                                                @@@@@@#&@@@@@@@@@        \n" +
                    "                                           .(@@&#&@&&&&&%@@@@@@@*        \n" +
                    "                                           &%&&&%&&&&&&&&&@@#            \n" +
                    "                                            .*%@@@%@@%, ..               \n" +
                    "                                                  .                      ";
    static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    static final String INDENT = "    ";
    private final Scanner sc;

    /**
     * Class constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a String s in a chat window.
     *
     * @param s The String to be printed.
     */
    public void printWindow(String s) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + s);
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the Bye message in a chat window.
     */
    public void printBye() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.print(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the introduction message with SOCCAT logo.
     * Messages are printed in a chat window.
     */
    public void intro() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(SOCCAT);
        System.out.println("    Hello! I'm Soccat Duke\n" +
                "    What do you meow?");
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the task marked as done in a chat window.
     *
     * @param task The task that is marked as done.
     */
    public void showMarkDone(Task task) {
        printWindow("Nice! I've marked this task as done: \n" +
                "      " + task);
    }

    /**
     * Prints the added task in a chat window.
     * It also tells the number of items in the list after addition.
     *
     * @param addedTask The task that is added.
     * @param listSize The number of items in the list.
     */
    public void showAdded(Task addedTask, int listSize) {
        printWindow("Got it. I've added this task:\n" +
                INDENT + "  " + addedTask + "\n" +
                INDENT +
                String.format("Now you have %d tasks in the in the list", listSize));
    }

    /**
     * Prints the deleted task in a chat window.
     * It also tells the number of items in the list after deletion.
     *
     * @param deletedTask The task that is deleted.
     * @param listSize The number of items in the list.
     */
    public void showDeleted(Task deletedTask, int listSize) {
        printWindow("Noted. I've removed this task: \n" +
                "      " + deletedTask +
                String.format("\n     Now you have %d tasks in the list.", listSize));
    }

    /**
     * Prints the loading error message in a chat window.
     */
    public void showLoadingError() {
        printWindow("Oops, error in loading the tasks! "
                + "Please check the duke.txt file");
    }

    /**
     * Prints the tasks in the list in a chat window.
     *
     * @param tasks The TaskList object with the list of tasks.
     */
    public void showList(TaskList tasks) {
        printWindow(tasks.getListAsString());
    }

    /**
     * Prints the error message in a chat window.
     *
     * @param e The error in which message is printed.
     */
    public void showError(Exception e) {
        printWindow(e.getMessage());
    }

    /**
     * Takes in the user command input in String format.
     * If no command is inputted, "" is returned.
     *
     * @return String command user inputted.
     */
    public String readCommand() {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }
}
