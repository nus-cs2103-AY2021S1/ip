import task.Task;

import java.util.Scanner;

/**
 * Ui is a class that provides interaction with the user.
 */
public class Ui {
    private Scanner scanner;
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Creates an instance of the Ui.
     */
    public Ui(){
        scanner = new Scanner(System.in);
    }

    /**
     * Method to print the welcome message for the user.
     */
    public static void printWelcome(){
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today?");
        printLineSeparator();
    }

    /**
     * Method to print the line separator.
     */
    public static void printLineSeparator(){
        System.out.println("----------------------------------------------------");
    }

    /**
     * Method to print a newly added Task.
     * @param t Newly added Task.
     */
    public static void printAddedTask(Task t){
        printLineSeparator();
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t\t" + t);
        printLineSeparator();
    }

    /**
     * Method to print a removed Task.
     * @param t Removed Task
     * @param taskListSize Remaining number of Tasks in Duke.
     */
    public static void printRemovedTask(Task t, int taskListSize){
        System.out.println("\t" + "Noted. I've removed this task:");
        System.out.println("\t\t" + t);
        System.out.println("\t" + "Now you have " + taskListSize + " tasks in the list.");
        printLineSeparator();
    }

    /**
     * Method to print a done message for a Task.
     * @param t Task that is done.
     */
    public static void printDoneTask(Task t){
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t\t" + t);
        printLineSeparator();
    }

    /**
     * Method to print an error message for files.
     * @param errorMessage Error message
     */
    public static void printFileError(String errorMessage){
        System.out.println("\t" + errorMessage);
        printLineSeparator();
    }

    /**
     * Method to print error message for exceptions.
     * @param errorMessage Error message
     */
    public static void printException(String errorMessage){
        System.out.println("\t" + errorMessage);
        printLineSeparator();
    }

    /**
     * Method to print bye.
     */
    public static void printBye(){
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    /**
     * Method to print the list of Tasks.
     * @param taskList List of Tasks
     */
    public static void printTaskList(TaskList taskList){
        for(int i = 0; i < taskList.size(); i++){
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
        }
        printLineSeparator();
    }

    /**
     * Method to get input from the user.
     * @return The next line of the user input.
     */
    public String getInput(){
        return scanner.nextLine();
    }

}
