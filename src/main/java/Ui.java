import task.Task;

import java.util.Scanner;

public class Ui {
    // Interactions with the user! :D
    // All the printing tasks
    private Scanner scanner;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui(){
        scanner = new Scanner(System.in);
    }
    public static void printWelcome(){
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you today?");
        printLineSeparator();
    }

    public static void printLineSeparator(){
        System.out.println("----------------------------------------------------");
    }

    public static void printAddedTask(Task t){
        printLineSeparator();
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t\t" + t);
        printLineSeparator();
    }

    public static void printRemovedTask(Task t, int taskListSize){
        System.out.println("\t" + "Noted. I've removed this task:");
        System.out.println("\t\t" + t);
        System.out.println("\t" + "Now you have " + taskListSize + " tasks in the list.");
        printLineSeparator();
    }

    public static void printDoneTask(Task t){
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t\t" + t);
        printLineSeparator();
    }

    public static void printFileError(String errorMessage){
        System.out.println("\t" + errorMessage);
        printLineSeparator();
    }

    public static void printException(String errorMessage){
        System.out.println("\t" + errorMessage);
        printLineSeparator();
    }

    public static void printBye(){
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void printTaskList(TaskList taskList){
        for(int i = 0; i < taskList.size(); i++){
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
        }
        printLineSeparator();
    }

    public String getInput(){
        return scanner.nextLine();
    }

}
