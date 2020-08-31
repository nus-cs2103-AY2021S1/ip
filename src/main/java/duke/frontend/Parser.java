package duke.frontend;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * Deals with parsing and making sense of user commands.
 */
public class Parser {
    private final TaskList taskList;
    private static final String dashline = "--------------------------------------------------------------------------";

    /**
     * Constructs an instance of Parser that adds, retrieves and delete tasks from taskList.
     * @param taskList taskList that contains and handles the user's list of Tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses user input commands and handles them appropriately. 
     * Feedbacks to user if commands are not understandable/ in the wrong format.
     * 
     * Handles the following command types: bye, done, delete, todo, deadline and event.
     */
    public void parseInputCommands() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        
        while (!next.equals("bye")){
        // "bye" breaks the while loop and causes the program to exit()
            String[] splitNext = next.split(" ", 2);
            
            if (next.equals("list")) {
            // "list" prints the task list
                this.taskList.list();
                
            } else if (splitNext[0].equals("done")) {
            // "done" checks off boxes, need to check for input errors
                try {
                    this.taskList.markTaskAsDone(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(dashline + "\n\u2639 Please indicate which task you'd like to check off!");
                }
                
            } else if (splitNext[0].equals("delete")) {
            // to "delete" tasks from the taskList
                try {
                    this.taskList.deleteTask(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(dashline + "\n\u2639 Please indicate which task you'd like to delete!");
                }
                
            } else if (splitNext[0].equals("todo") || splitNext[0].equals("deadline") || splitNext[0].equals("event")){
                // for ToDos, Deadlines, Events
                try {
                    this.taskList.add(next, false, true);
                } catch (IllegalArgumentException ex) {
                    System.out.println(dashline + "\n\u2639 " + ex.getMessage() + "\n" + dashline);
                }
            } else {
                System.out.println(dashline + "\n\u2639 Sorry I don't know what that means!\n" + dashline);
            }
            next = sc.nextLine();
        }
        sc.close();
    }
}
