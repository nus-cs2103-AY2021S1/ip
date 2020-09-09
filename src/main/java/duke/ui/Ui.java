package duke.ui;
import duke.logic.TaskList;
import duke.task.Task;

public class Ui {

    protected static TaskList tasks;

    /**
     * Constructor for Ui, initialises allowing for user inputs
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints previously saved task list for user
     */
    public static String showStartingTaskList() {
        if (tasks.size() > 0) {
            return "\nYou have a saved list! Here: \n" + tasks.toString();
        } else {
            return "";
        }
    }

//    /**
//     * Gets user input to Moco
//     *
//     * @return new user input/command.
//     */
//    public String getInput() {
//        return userInput.nextLine();
//    }

    /**
     * Prints statements to user when Moco starts up (initialises).
     */
    public String startBot() {
//        printBorder();
//        System.out.println("Hello I'm Moco, a task list bot to help you stay on top of your tasks!\n");
//        System.out.println("What can I do for you?\n");
//        printBorder();
        return "Hello I'm Moco, a task list bot to help you stay on top of your tasks!\n"
                + "What can I do for you?\n";
    }

    /**
     * Prints statements to user when Moco stops (end).
     */
    public String stopBot() {
        //printBorder();
        //System.out.println("Bye. Moco hopes to see you again soon!");
        //printBorder();
        return "Bye. Moco hopes to see you again soon!";
    }

    /**
     * Prints statements to user when user says "hi" or "hello".
     */
    public static String printGreeting() {
//        printBorder();
//        System.out.println("Hello! My name is Moco, I am excited to help! c:");
//        printBorder();
        return "Hello! My name is Moco, I am excited to help! c:";
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
    public String showTaskList(TaskList tasks) {
//        printBorder();
//        System.out.println("Here are the tasks in your list:");
//        System.out.println(tasks);
//        printBorder();
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Prints a prompt to congratulate user for completing specified task
     *
     * @param t specified task that is completed
     */
    public String doneTask(Task t) {
//        printBorder();
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(t);
//        printBorder();
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Prints requested task list for user
     * for "Find" command with specified keyword
     *
     * @param taskList tasks to print as a list
     */
    public String findTasks(TaskList taskList) {
//        System.out.println("Here are the tasks in your list with your requested keyword: \n");
//        System.out.println(taskList);
//        printBorder();
        return "Here are the tasks in your list with your requested keyword: \n"
                + taskList;
    }

    /**
     * Prints confirmation that specified task has been deleted
     *
     * @param task task to be deleted
     */
    public String deleteTask(Task task) {
//        System.out.println("Noted. I've removed this task:\n" + task);
//        printBorder();
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Prints confirmation that specified task has been added
     * to specified task list, along with current number of tasks.
     *
     * @param task  task to be deleted
     * @param tasks taskList that task has been added to
     */
    public String addTask(Task task, TaskList tasks) {
//        System.out.println("Got it. I've added this task:\n" + task);
//        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
//        printBorder();
        String result = "Got it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.\n";
        return result;
    }
}