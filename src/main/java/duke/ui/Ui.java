package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Scanner to take in user's input.
     */
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Greeting message to the user.
     */
    public void greetUser() {
        String logo = "Duke Melvin";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
    }

    /**
     * Goodbye message to the user.
     */
    public void sayGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the items within the list.
     *
     * @param taskList list containing all the tasks.
     */
    public void printList(TaskList taskList) {
        List<Task> ls = taskList.getTasks();
        for (int x = 0; x < ls.size(); x++) {
            System.out.println(x + 1 + ":" + ls.get(x).toString());
        }
    }

    /**
     * @return string containing the user's input.
     */
    public String readCommand() {
        return in.nextLine();
    }
}
