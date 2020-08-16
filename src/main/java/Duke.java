import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();
    private static final String starline = "**************************************************************************";
    private static final String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public static void greet() {
        System.out.println(starline +
                "\nWelcome! I am \n" + logo +
                "\nHere are some magic words to get you going: " +
                "\nTo view your tasks, say 'list'." +
                "\nTo check off a task, say 'done <task number>'." +
                "\nTo leave, say 'bye'." +
                "\nOtherwise, send me new tasks to add them to your todo list!\n" +
                starline);
    }

    public static void awaitInputCommand() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        // "bye" breaks the while loop and the program to exit
        while (!next.equals("bye")){
            String[] splitNext = next.split(" ", 2);

            // "list" prints the task list
            if (next.equals("list")) {
                taskList.list();

            // "done" checks off boxes, need to check for input errors
            } else if (splitNext[0].equals("done")) {
                try {
                    int index = Integer.parseInt(splitNext[1]);
                    taskList.markTaskAsDone(index - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("The magic word 'done' is reserved for checking off tasks! " +
                            "Please avoid using it at the start of a task name.");
                }

            // for Tasks, ToDos, Deadlines and Events
            } else {
                taskList.add(next);
            }
            next = sc.nextLine();
        }
        sc.close();
    }

    public static void exit() {
        System.out.println(
                        " _____  ___  ___  _____   \n" +
                        "|  __ \\ \\  \\/  / |  ___| \n" +
                        "| | / /  \\    /  |  |__  \n" +
                        "| | \\ \\   |  |   |  ___| \n" +
                        "| |_/ /   |  |   |  |__  \n" +
                        "|____/    |__|   |_____| \n"
        );
        System.out.println("Hope you have a productive day ahead! :))");
    }

    public static void main(String[] args) {
        greet();
        awaitInputCommand();
        exit();
    }
}
