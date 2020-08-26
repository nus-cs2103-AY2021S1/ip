package duke.ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * A class that deals with all interactions with the user.
 */
public class Ui {
    
    private static String greeting = "Hello~ I'm Duke!\n" + "What can I do for you?";
    private static String farewell = "Goodbye~";
    private static String doneMessage = "Nice! I've set this task as done~";
    private static String addTaskMessage = "Got it~ I've added this task:";
    private static String numberOfTaskMessage = "You now have %d tasks in the list~";
    private static String removeTaskMessage = "Alright~ I've removed this task:";
    private static String seperator = "____________________________________________________________";
    private static String listMessage = "Here are your tasks~";
    
    private Scanner sc;
    
    
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * A function to print the welcome message when starting Duke.
     */
    public void greet() {
        System.out.println(greeting);
    }

    /**
     * A function to print the goodbye message when stopping Duke.
     */
    public void farewell() {
        System.out.println(seperator + "\n" + farewell + "\n" + seperator);
    }

    /**
     * A function to print the message when marking a task as done.
     * @param doneTask the Task marked as done.
     */
    public void doneText(Task doneTask) {
        System.out.println(seperator + "\n" + doneMessage + "\n" + doneTask + 
                "\n" + seperator);
    }

    /**
     * A function to print the message when adding a task to the list.
     * @param addTask the Task added to the list.
     * @param result the TaskList the task is added to.
     */
    public void addTaskText(Task addTask, TaskList result) {
        System.out.println(seperator + "\n" + addTaskMessage + "\n" + addTask +
                "\n" + String.format(numberOfTaskMessage, result.getSize()) + 
                "\n" + seperator);
    }

    /**
     * A function to print the message when deleting a task from the list.
     * @param deleteTask the Task deleted from the list.
     * @param result the TaskList the task is deleted from.
     */
    public void deleteTaskText(Task deleteTask, TaskList result) {
        System.out.println(seperator + "\n" + removeTaskMessage + "\n" + deleteTask +
                "\n" + String.format(numberOfTaskMessage, result.getSize()) +
                "\n" + seperator);
    }

    /**
     * A function to print all the tasks in the list.
     * @param taskList the TaskList from which all the tasks should be printed from.
     */
    public void listText(TaskList taskList) {
        System.out.println(seperator + "\n" + listMessage + "\n");
        taskList.printTaskList();
        System.out.println("\n" + seperator);
    }

    /**
     * A function to print an error message for the user.
     * @param e the error message.
     */
    public void printError(Exception e) {
        System.out.println(e);
    }

    /**
     * A function to scan the user's next input and convert it to lower case.
     * @return a string representing the user's input.
     */
    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }
    
    
}
