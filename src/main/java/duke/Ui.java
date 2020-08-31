package duke;

import java.util.Scanner;

/**
 * Ui class to deal with user interaction
 */
public class Ui {
    private static final Scanner sc = new Scanner(System.in);
    private void delimiter() {
        System.out.println("__________________________________________________________________");
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
     * Prints message when it operates
     */
    public void showInfo() {
        delimiter();
        System.out.println("    Hello, I'm DukeQ");
        System.out.println("    todo then your instructions e.g. todo read book");
        System.out.println("    deadline then your instructions e.g. deadline by 2020-09-01");
        System.out.println("    type 'event' followed by the description,");
        System.out.println("    then '/at', then timing in yyyy-MM-dd format");
        System.out.println("    done followed by the task number " +
                "ok I have marked it as done");
        System.out.println("    type list to see the list");
        System.out.println("    type find followed by keywords to search for tasks");
        System.out.println("    type bye to exit");
        delimiter();
    }

    /**
     * Prints list of existing tasks
     *
     * @param tasks List of tasks
     */
    public void printList(TaskList tasks) {
        delimiter();
        if (tasks.size() == 0) {
            System.out.println("    List is empty");
        } else {
            System.out.println("    Items in list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        delimiter();
    }

    /**
     * Prints appropriate message for task depending on action type
     *
     * @param task Task to be printed
     * @param action Action type; determines format of text that is printed
     */
    public void printTask(Task task, ActionType action) {
        delimiter();
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
        delimiter();
    }

    public void search(TaskList tasks, String input) {
        int count = 1;
        delimiter();
        System.out.println("    Matching tasks:");
        for (Task task: tasks.getList()) {
            String taskString = task.toString();
            if (taskString.contains(input)) {
                System.out.println("      " + count + ". " + taskString);
                count++;
            }
        }
        delimiter();
    }

    /**
     * Prints total number of tasks
     *
     * @param tasks List of tasks
     */
    public void printTotalTasks(TaskList tasks) {
        System.out.println("    Total tasks: " + tasks.size());
        delimiter();
    }

    /**
     * Prints goodbye message
     */
    public void goodbye() {
        delimiter();
        System.out.println("    Bye!");
        delimiter();
    }
}
