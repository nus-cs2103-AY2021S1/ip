import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String SEPARATOR = "    ____________________________________"
        + "________________________\n";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private final Scanner sc;
    private boolean hasCommand;
    private String[] input;
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
        input = sc.nextLine().trim().split(" ", 2);
        System.out.printf("%s", SEPARATOR);
    }

    private void addTask(String command, String taskDescription) {
        Task task;
        String[] nameAndTime;
        switch (command) {
            case TODO_COMMAND:
                task = new Todo(taskDescription);
                break;
            case DEADLINE_COMMAND:
                nameAndTime = taskDescription.split(" /by ", 2);
                if (nameAndTime.length == 1) return;
                task = new Deadline(nameAndTime[0], nameAndTime[1]);
                break;
            case EVENT_COMMAND:
                nameAndTime = taskDescription.split(" /at ", 2);
                if (nameAndTime.length == 1) return;
                task = new Event(nameAndTime[0], nameAndTime[1]);
                break;
            default:
                return;
        }
        tasks.add(task);
        System.out.printf("     Got it. I've added this task:\n"
                        + "       %s\n"
                        + "     Now you have %d %s in the list.\n%s",
                task, tasks.size(),
                tasks.size() == 1 ? "task" : "tasks", SEPARATOR);
    }

    private void printExit() {
        System.out.printf("     Bye. Hope to see you again soon!\n%s",
                SEPARATOR);
    }

    private void printTaskList() {
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i));
        if (tasks.size() == 0)
            System.out.println("     There are no tasks yet!");
        System.out.print(SEPARATOR);
    }

    private void setTaskDone(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.setDone();
        System.out.printf("     Nice! I've marked this task as done:\n"
                        + "       %s\n%s",
                task, SEPARATOR);
    }

    public void initialise() {
        hasCommand = true;
        printGreeting();

        while (hasCommand) {
            getCommand();
            String command = input[0];
            switch (command) {
                case EXIT_COMMAND:
                    hasCommand = false;
                    break;
                case LIST_COMMAND:
                    printTaskList();
                    break;
                case DONE_COMMAND:
                    int taskNum = Integer.parseInt(input[1]);
                    setTaskDone(taskNum);
                    break;
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    if (input.length == 1) break;
                    String taskDescription = input[1];
                    addTask(command, taskDescription);
                    break;
                default:
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