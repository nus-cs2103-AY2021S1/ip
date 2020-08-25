package duke;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import duke.exception.InvalidCommand;
import duke.parser.Parser;
import duke.tasks.Task;

public class Ui {
    private String logo;
    private String lines;
    private String defaultGreeting;
    private static final Scanner sc = new Scanner(System.in);
    private Parser commandParser;
    public Ui(Storage listStorage, TaskList taskList) {
        this.logo = " .d8888b.  888               888    888                  888888b.            888    \n" +
                "d88P  Y88b 888               888    888                  888  \"88b           888    \n" +
                "888    888 888               888    888                  888  .88P           888    \n" +
                "888        88888b.   8888b.  888888 888888 888  888      8888888K.   .d88b.  888888 \n" +
                "888        888 \"88b     \"88b 888    888    888  888      888  \"Y88b d88\"\"88b 888    \n" +
                "888    888 888  888 .d888888 888    888    888  888      888    888 888  888 888    \n" +
                "Y88b  d88P 888  888 888  888 Y88b.  Y88b.  Y88b 888      888   d88P Y88..88P Y88b.  \n" +
                " \"Y8888P\"  888  888 \"Y888888  \"Y888  \"Y888  \"Y88888      8888888P\"   \"Y88P\"   \"Y888 \n" +
                "                                                888                                 \n" +
                "                                           Y8b d88P                                 \n" +
                "                                            \"Y88P\"                                  ";
        this.lines = "    ____________________________________________________________";
        this.defaultGreeting = this.lines + "\n" + "     Hello! I'm Chatty Bot \n" + "     What can I do for you?\n" + lines + "\n";
        this.commandParser = new Parser();
    }

    public String getInput() {
        String nextLine = sc.nextLine();
        return nextLine;
    }

    public void printWelcome() {
        System.out.println("Hello from\n" + this.logo);
        System.out.println(defaultGreeting);
    }

    public void loadingFile() {

        System.out.println(this.lines);
        System.out.println("     Previously saved list (if any) loaded. You may enter your commands now:");
        System.out.println(this.lines);
    }

    public static void addDirectory() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     I see that you do not have a directory to store data. " +
                "Created one for you before we proceed.");
        System.out.println("    ____________________________________________________________");
    }

    public static void addDataFile() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     I see that this is your first time using Chatty Bot, " +
                "I have created a file to log your history from now on!");
        System.out.println("    ____________________________________________________________");
    }

    public void markAsDone(int taskIndex, TaskList currList) {
        System.out.println(this.lines);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + currList.get(taskIndex));
        System.out.println(this.lines);
    }

    public void addTask(Task newTask, TaskList currList) {
        System.out.println(this.lines + "\n" + "     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + String.valueOf(currList.numTask()) + " task(s) in the list.");
        System.out.println(this.lines);
    }

    public void listItems(TaskList currList) {
        System.out.println(this.lines);
        System.out.println("     Here are the tasks in your list:");
        currList.list();
        System.out.println(this.lines);
    }

    public void deleteTask(Task removeTask, TaskList currList) {
        System.out.println(this.lines);
        System.out.println("     Alright, the following task has been removed");
        System.out.println("     " + removeTask);
        System.out.println("     Now you have " + String.valueOf(currList.numTask()) + " task(s) in the list.");
        System.out.println(this.lines);
    }

    public static void commandError(InvalidCommand ex) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     "  + ex);
        System.out.println("    ____________________________________________________________");
    }

    public void goodBye() {
        String end_Greeting = this.lines + "\n" + "     Bye. Hope to see you again soon!\n" + this.lines;
        System.out.println(end_Greeting);
    }

    public void printFoundItems(TaskList foundTasks, String keyword) {
        System.out.println(this.lines);
        System.out.println("     Here are the matching tasks for keyword \"" + keyword + "\" in your list:");
        foundTasks.list();
        System.out.println(lines);
    }
}
