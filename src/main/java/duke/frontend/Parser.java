package duke.frontend;

import duke.task.TaskList;

import java.util.Scanner;

public class Parser {
    private final TaskList taskList;
    private static final String DASHLINE = "--------------------------------------------------------------------------";
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    
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
                    System.out.println(DASHLINE + "\n\u2639 Please indicate which task you'd like to check off!");
                }
                
            } else if (splitNext[0].equals("delete")) {
            // to "delete" tasks from the taskList
                try {
                    this.taskList.deleteTask(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(DASHLINE + "\n\u2639 Please indicate which task you'd like to delete!");
                }
                
            } else if (splitNext[0].equals("todo") || splitNext[0].equals("deadline") || splitNext[0].equals("event")){
            // for ToDos, Deadlines, Events
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
