import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private static Storage storage;
    private static TaskList taskList;
    private static final String starline = "**************************************************************************";
    private static final String dashline = "--------------------------------------------------------------------------";
    private static final String logo =
              " ____        _   \n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public static void greet() {
        System.out.println(starline +
                "\nWelcome! I am\n" + logo +
                "\nHere are some magic words to get you going:" +
                "\nTo add a todo, say 'todo <task description>'." +
                "\nTo add a deadline, say 'deadline <task description> /by <yyyy-mm-dd>'." +
                "\nTo add an event, say 'event <event description> /at <event location>'" +
                "\nTo view your tasks, say 'list'." +
                "\nTo check off a task, say 'done <task number>'." +
                "\nTo leave, say 'bye'.\n" +
                starline);
    }

    public static void awaitInputCommand() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        // "bye" breaks the while loop and causes the program to exit()
        while (!next.equals("bye")){
            String[] splitNext = next.split(" ", 2);

            // "list" prints the task list
            if (next.equals("list")) {
                taskList.list();

            // "done" checks off boxes, need to check for input errors
            } else if (splitNext[0].equals("done")) {
                try {
                    taskList.markTaskAsDone(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(dashline + "\n\u2639 Please indicate which task you'd like to check off!");
                }
            // to "delete" tasks from the taskList
            } else if (splitNext[0].equals("delete")) {
                try {
                    taskList.deleteTask(splitNext[1]);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println(dashline + "\n\u2639 Please indicate which task you'd like to delete!");
                }
                
            // for ToDos, Deadlines, Events
            } else if (splitNext[0].equals("todo") || splitNext[0].equals("deadline") || splitNext[0].equals("event")){
                try {
                    taskList.add(next, true);
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

    public static void exit() {
        System.out.println(
                        " _____  ___  ___  _____\n" +
                        "|  __ \\ \\  \\/  / |  ___|\n" +
                        "| | / /  \\    /  |  |__\n" +
                        "| | \\ \\   |  |   |  ___|\n" +
                        "| |_/ /   |  |   |  |__\n" +
                        "|____/    |__|   |_____|\n"
        );
        System.out.println("Hope you have a productive day ahead! :))");
    }
    
    public static void initialise() {
        try {
            storage = new Storage("./Data/saved-tasks.txt");
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not created. " + e);
        }
    }
    
    public static void save() {
        storage.save(taskList);
    }

    public static void main(String[] args) {
        initialise();
        greet();
        awaitInputCommand();
        save();
        exit();
    }
}
