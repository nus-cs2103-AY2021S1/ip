import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private static final String starline = "**************************************************************************";
    private static final String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    public Duke() {
        this.taskList = new TaskList();
    }

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

    public void awaitInputCommand() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")){
            String[] splitNext = next.split(" ", 2);
            if (next.equals("list")) {
                taskList.list();
            } else if (splitNext[0].equals("done")) {
                try {
                    int index = Integer.parseInt(splitNext[1]);
                    taskList.markTaskAsDone(index - 1);
                } catch (NumberFormatException ex1) {
                    System.out.println("The magic word 'done' is reserved for checking off tasks! " +
                            "Please avoid using it at the start of a task name.");
                } catch (IndexOutOfBoundsException ex2) {
                    System.out.println("You do not have this task yet! Type 'list' to check out your tasks.");
                }
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
        Duke duke = new Duke();
        greet();
        duke.awaitInputCommand();
        exit();
    }
}
