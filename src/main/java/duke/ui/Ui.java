package duke.ui;

import java.util.Scanner;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents an object that is responsible for user interaction.
 * This includes reading user input and displaying the correspondent output.
 */
public class Ui {
    private final String LINE_BREAK = "   ____________________________" +
            "________________________________";
    private final String SPACE1 = "    ";
    private final String SPACE2 = "     ";
    private final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String showWelcome() {
        return "Hello from\n" + LOGO + "\nHey, I'm Emilia \u2764 !\nWhat can I do for you?";
    }

    public String showError(String errorMessage) {
        return errorMessage;
    }

    public String showErrorLoad(String errorMessage) {
        return errorMessage + "\n" + "Initialize an empty TaskList!\n";
    }
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    public String readLine() {
        return sc.nextLine().trim().toLowerCase();
    }

    public String showBye() {
        return "Welcome back \u2764 !";
    }

    public String showList() {
        return "Please take a look at the tasks:";
    }

    public String showTask(int count, Task task) {
        return count + ". " + task;
    }

    public String showDone(Task task) {
        return "Understood, I've marked this task as done:\n" + task;
    }

    public String showDelete(Task task, TaskList list) {
        return "Understood, I've deleted this task\n" + task + "\n" + "You have " +
                list.getSize() + " tasks in your list now!";
    }

    public String showCheck() {
        return "Hey! I have printed out the tasks that match the date:";
    }

    public String showAdd(Task current, TaskList list) {
        return "Understood! I've added this task:\n" + current + "\n You have " +
                list.getSize() + " tasks in your list now!";
    }

    public String showFind() {
        return "Hey! I have printed out the tasks that match the name:";
    }

    public String showNothingFound() {
        return "Hmm, I didn't find anything that match your input";
    }

}
