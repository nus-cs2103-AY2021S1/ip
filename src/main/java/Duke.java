import java.time.DateTimeException;
import java.time.LocalDateTime;
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
    private static final String DELETE_COMMAND = "delete";

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

    private LocalDateTime toDateTime(String dateTime) {
        String[] dateTimeSplit = dateTime.split(" ");
        if (dateTimeSplit.length != 2)
            throw new DateTimeException("     ☹ OOPS!!! Your date needs to"
                    + " have this format:\n     \"YYYY-MM-DD HHMM\"");

        String[] date = dateTimeSplit[0].split("-");
        String time = dateTimeSplit[1];

        if (date.length != 3)
            throw new DateTimeException("     ☹ OOPS!!! Your date needs to"
                    + " have this format:\n     \"YYYY-MM-DD\"");
        if (time.length() != 4)
            throw new DateTimeException("     ☹ OOPS!!! Your time needs to"
                    + " have this format:\n     \"HHMM\"");
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));

            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException | DateTimeException e) {
            throw new DateTimeException("     ☹ OOPS!!! Please check that you've"
                    + " entered\n       the date and time correctly");
        }
    }

    private void addTask(String command, String taskDescription)
            throws InvalidDeadlineFormatException, InvalidEventFormatException {
        Task task;
        String[] nameAndTime;
        switch (command) {
            case TODO_COMMAND:
                task = new Todo(taskDescription);
                break;
            case DEADLINE_COMMAND:
                nameAndTime = taskDescription.split(" /by ", 2);
                if (nameAndTime.length == 1)
                    throw new InvalidDeadlineFormatException();
                task = new Deadline(nameAndTime[0], toDateTime(nameAndTime[1]));
                break;
            case EVENT_COMMAND:
                nameAndTime = taskDescription.split(" /at ", 2);
                if (nameAndTime.length == 1)
                    throw new InvalidEventFormatException();
                task = new Event(nameAndTime[0], toDateTime(nameAndTime[1]));
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

    private void setTaskDone(int taskNum) throws InvalidTaskException {
        if (taskNum < 1 || taskNum > tasks.size())
            throw new InvalidTaskException();
        Task task = tasks.get(taskNum - 1);
        task.setDone();
        System.out.printf("     Nice! I've marked this task as done:\n"
                        + "       %s\n%s",
                task, SEPARATOR);
    }

    private void deleteTask(int taskNum) throws InvalidTaskException {
        if (taskNum < 1 || taskNum > tasks.size())
            throw new InvalidTaskException();
        Task removed = tasks.remove(taskNum - 1);
        System.out.printf("     Noted. I've removed this task:\n       %s\n"
                        + "     Now you have %d %s in the list.\n%s",
                removed, tasks.size(),
                tasks.size() == 1 ? "task" : "tasks", SEPARATOR);
    }

    public void initialise() {
        hasCommand = true;
        printGreeting();

        while (hasCommand) {
            getCommand();
            String command = input[0];
            int taskNum;

            try {
                switch (command) {
                case EXIT_COMMAND:
                    hasCommand = false;
                    break;
                case LIST_COMMAND:
                    printTaskList();
                    break;
                case DONE_COMMAND:
                    taskNum = Integer.parseInt(input[1]);
                    setTaskDone(taskNum);
                    break;
                case DELETE_COMMAND:
                    taskNum = Integer.parseInt(input[1]);
                    deleteTask(taskNum);
                    break;
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    if (input.length == 1)
                        throw new EmptyMessageException(command);
                    String taskDescription = input[1];
                    addTask(command, taskDescription);
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (DukeException | DateTimeException | NumberFormatException e) {
                System.out.printf("%s\n%s", e.getMessage(), SEPARATOR);
            }
        }

        printExit();
    }

    public static void main(String[] args) {
        new Duke().initialise();
    }
}