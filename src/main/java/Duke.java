import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final Scanner sc;
    private final String SEPARATOR = "    ____________________________________"
        + "________________________\n";
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private boolean hasCommand;
    private String command;
    private List<Task> tasks;

    public Duke() {
        sc = new Scanner(System.in);
        hasCommand = false;
        tasks = new ArrayList<>();
    }

    private void printGreeting() {
        System.out.printf("%s     Hello! I'm Duke\n"
                + "     What can I do for you?\n%s", SEPARATOR, SEPARATOR);
    }

    private void getCommand() {
        System.out.println();
        command = sc.nextLine().trim();
        System.out.printf("%s", SEPARATOR);
    }

    private void addTask(String taskName) {
        tasks.add(new Task(taskName));
        System.out.printf("     added: %s\n%s", taskName, SEPARATOR);
    }

    private void printExit() {
        System.out.printf("     Bye. Hope to see you again soon!\n%s",
                SEPARATOR);
    }

    private void printTaskList() {
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("     %d. %s\n", i + 1, tasks.get(i));
        System.out.print(SEPARATOR);
    }

    public void initialise() {
        hasCommand = true;
        printGreeting();

        while (hasCommand) {
            getCommand();
            switch (command) {
                case EXIT_COMMAND:
                    hasCommand = false;
                    break;
                case LIST_COMMAND:
                    printTaskList();
                    break;
                default:
                    addTask(command);
                    break;
            }
        }

        printExit();
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        new Duke().initialise();
    }
}