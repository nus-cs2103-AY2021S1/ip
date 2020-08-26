import java.util.ArrayList;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________" + "\n";

    /**
     * Constructor that creates a Ui object.
     */
    public Ui() {}
    
    private void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a welcome message.
     */
    public void printHi() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(LINE + greeting + LINE);
    }

    /**
     * Prints a goodbye message.
     */
    public void printBye() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(LINE + goodbye + "\n" + LINE);
    }

    /**
     * Prints a message to inform the user that a Task has been added to TaskList.
     * @param task the Task to be added to TaskList.
     * @param taskList the Tasklist to add the Task to.
     */
    public void printAdd(Task task, TaskList taskList) {
        System.out.println(LINE
                + "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size()) + "\n"
                + LINE);
    }

    /**
     * Prints a message to inform the user that a Task has been deleted from the TaskList.
     * @param index the index of the Task to be deleted.
     * @param taskList the TaskList to delete the Task from.
     */
    public void printDelete(int index, TaskList taskList) {
        System.out.println(LINE
                + "Noted. I've removed this task: " + "\n"
                + taskList.getTask(index).toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size() - 1) + "\n"
                + LINE);
    }

    /**
     * Prints a message to inform the user of an error in the application.
     * @param e the Exception that was thrown by the application.
     */
    public void printError(Exception e) {
        System.out.println(LINE + "Error: " + e + "\n" + LINE);
    }

    /**
     * Overloaded method which also prints a specified message to inform the user
     * of an error in the application.
     * @param message the message that the application wants to print for the user.
     */
    public void printError(String message) {
        System.out.println(LINE + "Error: " + message + "\n" + LINE);
    }

    /**
     * Prints out all Tasks currently in the TaskList.
     * @param taskList the TaskList which contain the Tasks to be printed out.
     */
    public void showTasks(TaskList taskList) {
        if(taskList.size() > 0) {
            System.out.println(LINE + "Here are the tasks in your list:");
            for (int i = 1; i <= taskList.size(); i++) {
                //print out task with numbering
                System.out.println(String.format("%s. %s", i, taskList.getTask(i).toString()));
            }
        } else { //no tasks
            System.out.println("You have no tasks!");
        }
        printLine();
    }

    /**
     * Prints a message to inform the user that a save file is being read.
     */
    public void loadTasks() {
        System.out.println(LINE + "Reading saved file..." + "\n" + LINE);
    }

    /**
     * Prints a specified message to inform the user that an operation succeeded.
     * @param message the message to be printed out.
     */
    public void printSuccess(String message) {
        System.out.println(LINE + "Success: " + message + "\n" + LINE);
    }

    /**
     * Prints a message to inform the user that a specific Task in the TaskList
     * has been marked as completed.
     * @param index the index of the Task in the TaskList.
     * @param taskList the TaskList which contains the Task to be marked as completed.
     */
    public void markDone(int index, TaskList taskList) {
        Task task = taskList.getTask(index);
        System.out.println(LINE + "Nice! I've marked this task as done:" +  "\n" +
                task.toString() + "\n" + LINE);
    }

    /**
     * Prints a list of Tasks in the current TaskList that matches a specified word.
     * @param word the word to search for in the TaskList.
     * @param taskList the TaskList which contain the Tasks to be filtered
     *                 according to whether it contains the specified word.
     */
    public void find(String word, TaskList taskList) {
        TaskList temp = new TaskList();
        for(int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if(task.getName().contains(word)) {
                temp.addTask(task);
            }
        }
        if(temp.size() > 0) {
            System.out.println(LINE + "Here are the matching tasks in your list:");
            for (int i = 1; i <= temp.size(); i++) {
                //print out task with numbering
                System.out.printf("%s. %s%n", i, temp.getTask(i).toString());
            }
        } else { //no matching tasks
            System.out.println("You have no matching tasks!");
        }
        printLine();
    }
}
