import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String DIVIDER = "____________________________________________________________";
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_DUE = "due";
    private Scanner sc;

    public InputHandler(Scanner sc) {
        this.sc = sc;
        handleStart();
    }

    private static void handleStart() {
        String startMsg =
            DIVIDER + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(startMsg);
    }

    public void run() {
        String input;
        while (!(input = sc.nextLine()).equals(CMD_EXIT)) {
            handleInput(input);
        }
        handleExit();
    }

    public void handleInput(String in) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
        case (CMD_LIST):
            handleList();
            break;
        case (CMD_DONE):
            handleDone(Integer.parseInt(input[1]));
            break;
        case (CMD_DELETE):
            handleDelete(in);
            break;
        case (CMD_DUE):
            handleDue(in);
            break;
        default: // for invalid commands and adding of tasks
            try {
                handleTask(in, cmdWord);
            } catch (DukeException e) {
                System.out.println(
                    DIVIDER + "\n" +
                    e.getMessage() + "\n" +
                    DIVIDER
                );
            }
        }
    }

    private void handleDue(String in) {
        String dateStr = in.replaceFirst("due ", "");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateStr);
            String formattedDate = DateTimeParsing.localDateToString(date);
            ArrayList<String> filteredTasks = new ArrayList<>();

            int len = taskList.size();
            for (int i = 1; i <= len; i++) {
                Task task = taskList.get(i - 1);
                if (task.isDueOn(date)) {
                    String output = i + "." + task.toString();
                    filteredTasks.add(output);
                }
            }

            String firstLine = filteredTasks.size() == 0
                    ? "There are no tasks due on " + formattedDate + "!"
                    : "These are the tasks due on " + formattedDate + ":";

            String msg =
                DIVIDER + "\n" +
                firstLine + "\n" +
                String.join("\n", filteredTasks) + "\n" +
                DIVIDER;

            System.out.println(msg);
        } catch (DateTimeParseException | NumberFormatException e) {
            String errMsg =
                "Please key in a valid date format.\n" +
                "due *yyyy-mm-dd*";
            System.out.println(
                DIVIDER + "\n" +
                errMsg + "\n" +
                DIVIDER
            );
        }
    }

    private void handleDelete(String in) {
        try {
            int index =
                Integer.parseInt(
                    in.replaceFirst(CMD_DELETE, "").trim()
                );
            Task task = taskList.remove(index - 1);
            int len = taskList.size();
            String msg =
                    DIVIDER + "\n" +
                    "Noted. I've removed this task:\n" +
                    "  " + task.toString() + "\n" +
                    "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.\n" +
                    DIVIDER;
            System.out.println(msg);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(
                DIVIDER + "\n" +
                "Please input a valid index." + "\n" +
                DIVIDER
            );
        }
    }

    private void handleList() {
        int len = taskList.size();
        String firstLine = len == 0
            ? "There are no tasks in your list!"
            : "Here are the tasks in your list:";
        System.out.println(DIVIDER);
        System.out.println(firstLine);
        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            String output = i + "." + task.toString();
            System.out.println(output);
        }
        System.out.println(DIVIDER);
    }

    private void handleDone(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println(
            DIVIDER + "\n" +
            "Nice! I've marked this task as done\n" +
            "  " + task.toString() + "\n" +
            DIVIDER
        );
    }

    private void handleTask(String in, String cmdWord) throws DukeException {
        String taskDetails = in.replaceFirst(cmdWord, "").trim();
        switch (cmdWord) {
        case "deadline":
            addNewTask(TaskType.Deadline, taskDetails);
            break;
        case "event":
            addNewTask(TaskType.Event, taskDetails);
            break;
        case "todo":
            addNewTask(TaskType.Todo, taskDetails);
            break;
        default:
            String errMsg = "I'm sorry, but I don't know what that means :-(";
            throw new InvalidCommandException(errMsg);
        }
    }

    private void addNewTask(TaskType type, String taskDetails) throws InvalidTaskException {
        Task task = Task.createTask(type, taskDetails);
        taskList.add(task);
        handleTaskCreated(task);
    }

    private void handleTaskCreated(Task task) {
        int len = taskList.size();
        String msg =
            DIVIDER + "\n" +
            "Got it. I've added this task: \n" +
            "  " + task.toString() + "\n" +
            "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.\n" +
            DIVIDER;
        System.out.println(msg);
    }

    private void handleExit() {
        String exitMsg =
            DIVIDER + "\n" +
            "Bye. Hope to see you again soon!\n" +
            DIVIDER;
        System.out.println(exitMsg);
    }
}
