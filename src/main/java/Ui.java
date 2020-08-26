import java.util.ArrayList;

/**
 * Class that handles system's interactions and output with user
 *
 */
public class Ui {

    /**
     * void function to greet and introduce to user
     *
     */
    public void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * void printing function that prints a string that adheres to line and indentation format of Duke
     *
     * @param string
     */
    public static void print(String string) {
        showLine();
        System.out.println(string);
        showLine();
    }

    /**
     * takes in an ArrayList of tasks and prints each task one by one
     *
     * @param TaskList
     */
    public static void printList(ArrayList<Task> TaskList) {
        showLine();
        for (Task task : TaskList) {
            System.out.println("     " + (TaskList.indexOf(task) + 1) + "." + task);
        }
        showLine();
    }

    /**
     * Exit function for Duke
     *
     */
    public void Bye() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Helper function to assist with line and spacing format
     *
     */
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}
