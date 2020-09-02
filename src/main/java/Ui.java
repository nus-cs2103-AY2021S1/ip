import java.util.ArrayList;

/**
 * Handles the user interface provided to the user.
 * The Ui class provides utility methods to show various message types.
 */
public class Ui {

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
    }

    /**
     * Prints all the tasks stored in the given list.
     *
     * @param task_list The list containing all the tasks.
     * @throws DukeInvalidCommandException If the task list is empty.
     */
    public void displayTaskList(ArrayList<Task> task_list) throws DukeInvalidCommandException {
        if (task_list.size() == 0) {
            throw new DukeInvalidCommandException("There are no task_list in the list");
        }

        System.out.println("    ____________________________________________________________\n" +
                           "     Here are the tasks in your list:");
        for (int i = 1; i <= task_list.size(); i++) {
            System.out.println("     " + i + "." + task_list.get(i-1));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the error message.
     *
     * @param e The error that is being raised.
     */
    public void printError(Exception e) {
        System.out.println(e.toString());
        System.out.println("Please try again.");
    }

    /**
     * Prints the invalid date format error message.
     */
    public void printInvalidDateFormatError() {
        System.out.println("Invalid Date Format!");
        System.out.println("Please enter the date as yyyy-mm-dd");
    }


    /**
     * Displays a greeting by Duke when the program first starts up.
     */
    public void hello() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" + logo +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(hello);

    }

    /**
     * Displays a bye message when client inputs bye, followed by..
     * the termination of the Duke program.
     */
    public void bye() {
        String bye = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(bye);
        System.exit(0);
    }

}
