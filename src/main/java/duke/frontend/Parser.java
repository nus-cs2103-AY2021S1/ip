package duke.frontend;

import duke.task.TaskList;

import java.util.Scanner;

public class Parser {
    private final TaskList taskList;
    private static final String dashline = "--------------------------------------------------------------------------";
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    
    public void parseInputCommands() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        // "bye" breaks the while loop and causes the program to exit()
        while (!next.equals("bye")){
            String[] splitNext = next.split(" ", 2);

            // "list" prints the task list
            if (next.equals("list")) {
                this.taskList.list();

                // "done" checks off boxes, need to check for input errors
            } else if (splitNext[0].equals("done")) {
                try {
                    this.taskList.markTaskAsDone(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(dashline + "\n\u2639 Please indicate which task you'd like to check off!");
                }
                // to "delete" tasks from the taskList
            } else if (splitNext[0].equals("delete")) {
                try {
                    this.taskList.deleteTask(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(dashline + "\n\u2639 Please indicate which task you'd like to delete!");
                }

                // for ToDos, Deadlines, Events
            } else if (splitNext[0].equals("todo") || splitNext[0].equals("deadline") || splitNext[0].equals("event")){
                try {
                    this.taskList.add(next, true);
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
