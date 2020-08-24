package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Ui class to deal with user interaction
 */
public class Ui {
    private static final Scanner sc = new Scanner(System.in);
    private void showLine() {
        System.out.println("   __________________________________________________________________");
    }

    /**
     * Returns next line of user input
     *
     * @return Next line of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello, I'm Duke");
        System.out.println("    I can help you keep track of all your tasks! ☆*:.｡.o(≧▽≦)o.｡.:*☆");
        showLine();
        System.out.println("    How to add tasks to the list:");
        System.out.println("    ToDo - type 'todo' followed by the description");
        System.out.println("    Deadline - type 'deadline' followed by the description,");
        System.out.println("    then '/by', then due date in yyyy-MM-dd format");
        System.out.println("    Event - type 'event' followed by the description,");
        System.out.println("    then '/at', then timing in yyyy-MM-dd format");
        System.out.println("    Type 'done' followed by the task number " +
                "and I'll mark it as done");
        System.out.println("    Type 'list' to see the list");
        System.out.println("    Type 'find' followed by keyword to search for tasks");
        System.out.println("    Type 'bye' to exit");
        showLine();
    }

    /**
     * Prints list of existing tasks
     *
     * @param tasks List of tasks
     */
    public void printList(TaskList tasks) {
        showLine();
        if (tasks.size() == 0) {
            System.out.println("    List is empty");
        } else {
            System.out.println("    Items in list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        showLine();
    }

    /**
     * Prints appropriate message for task depending on action type
     *
     * @param task Task to be printed
     * @param action Action type; determines format of text that is printed
     */
    public void printTask(Task task, ActionType action) {
        showLine();
        switch(action){
        case MARK_DONE:
            System.out.println("    Task marked complete:");
            break;
        case DELETE:
            System.out.println("    Task deleted:");
            break;
        default:
            System.out.println("    Added: ");
        }
        System.out.println("      " + task.toString());
        showLine();
    }

    public void search(TaskList tasks, String input) {
        int count = 1;
        showLine();
        System.out.println("    Matching tasks:");
        for (Task task: tasks.getList()) {
            String taskString = task.toString();
            if (taskString.contains(input)) {
                System.out.println("      " + count + ". " + taskString);
                count++;
            }
        }
        showLine();
    }

    /**
     * Prints total number of tasks
     *
     * @param tasks List of tasks
     */
    public void printTotalTasks(TaskList tasks) {
        System.out.println("    Total tasks: " + tasks.size());
        showLine();
    }

    /**
     * Prints goodbye message
     */
    public void goodbye() {
        showLine();
        System.out.println("    See you again soon (hopefully)! :>");
        showLine();
    }
}
