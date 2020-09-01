package duke.frontend;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * Deals with parsing and making sense of user commands.
 */
public class Parser {
    private static final String DASHLINE = "--------------------------------------------------------------------------";
    private final TaskList taskList;

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

        // "bye" breaks the while loop and causes the program to exit()
        while (!next.equals("bye")) {
            String[] splitNext = next.split(" ", 2);

            // "list" prints the task list
            if (next.equals("list")) {
                this.taskList.list();

            // "done" checks off boxes, need to check for input errors
            } else if (splitNext[0].equals("done")) {
                try {
                    this.taskList.markTaskAsDone(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(DASHLINE + "\n\u2639 Please indicate which task you'd like to check off!");
                }

            // to "delete" tasks from the taskList
            } else if (splitNext[0].equals("delete")) {
                try {
                    this.taskList.deleteTask(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(DASHLINE + "\n\u2639 Please indicate which task you'd like to delete!");
                }

            } else if (splitNext[0].equals("find")) {
                if (splitNext.length == 1) {
                    this.taskList.find("");
                } else {
                    this.taskList.find(splitNext[1]);
                }

            // for ToDos, Deadlines, Events
            } else if (splitNext[0].equals("todo") || splitNext[0].equals("deadline") || splitNext[0].equals("event")) {
                try {
                    this.taskList.add(next, false, true);
                } catch (IllegalArgumentException ex) {
                    System.out.println(DASHLINE + "\n\u2639 " + ex.getMessage() + "\n" + DASHLINE);
                }
            } else {
                System.out.println(DASHLINE + "\n\u2639 Sorry I don't know what that means!\n" + DASHLINE);
            }
            next = sc.nextLine();
        }
        sc.close();
    }
}
