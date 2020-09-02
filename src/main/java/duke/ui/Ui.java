package duke.ui;
import duke.logic.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Ui {
    private Scanner userInput;
    private String lastError;

    /**
     * Constructor for Ui, initialises allowing for user inputs
     */
    public Ui() {
        userInput = new Scanner(System.in);
    }

    /**
     * Gets user input to Moco
     *
     * @return new user input/command.
     */
    public String getInput() {
        return userInput.nextLine();
    }

    /**
     * Prints statements to user when Moco starts up (initialises).
     */
    public void startBot() {
        printBorder();
        System.out.println("Hello I'm Moco, a task list bot to help you stay on top of your tasks!\n");
        System.out.println("What can I do for you?\n");
        printBorder();
    }

    /**
     * Prints statements to user when Moco stops (end).
     */
    public void stopBot() {
        printBorder();
        System.out.println("Bye. Moco hopes to see you again soon!");
        printBorder();
    }

    /**
     * Prints statements to user when user says "hi" or "hello".
     */
    public void printGreeting() {
        printBorder();
        System.out.println("Hello! My name is Moco, I am excited to help! c:");
        printBorder();
    }

    /**
     * Prints border frame for user (easier readability)
     */
    public void printBorder() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints requested task list for user
     *
     * @param tasks tasks to print as a list
     */
    public void printTaskList(TaskList tasks) {
        printBorder();
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
        printBorder();
    }

    /**
     * Prints a prompt to congratulate user for completing specified task
     *
     * @param t specified task that is completed
     */
    public void doneTask(Task t) {
        printBorder();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        printBorder();
    }

    /**
     * Prints requested task list for user
     * for "Find" command with specified keyword
     *
     * @param taskList tasks to print as a list
     */
    public void findTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list with your requested keyword: \n");
        System.out.println(taskList);
        printBorder();
    }

    /**
     * Prints confirmation that specified task has been deleted
     *
     * @param task task to be deleted
     */
    public void deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
        printBorder();
    }

    /**
     * Prints confirmation that specified task has been added
     * to specified task list, along with current number of tasks.
     *
     * @param task  task to be deleted
     * @param tasks taskList that task has been added to
     */
    public void addTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        printBorder();
    }
}