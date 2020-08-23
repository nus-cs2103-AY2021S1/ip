import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Duke {

    // General style properties of the chatbot.
    private static final String LEFT_MARGIN = "    ";
    private static final String LEFT_MARGIN_DOUBLE = LEFT_MARGIN + LEFT_MARGIN;
    private static final String LOGO = "\n" + LEFT_MARGIN
            + "███╗   ███╗     █████╗     ██████╗     ██╗     ██████╗ \n" + LEFT_MARGIN
            + "████╗ ████║    ██╔══██╗    ██╔══██╗    ██║    ██╔═══██╗\n" + LEFT_MARGIN
            + "██╔████╔██║    ███████║    ██████╔╝    ██║    ██║   ██║\n" + LEFT_MARGIN
            + "██║╚██╔╝██║    ██╔══██║    ██╔══██╗    ██║    ██║   ██║\n" + LEFT_MARGIN
            + "██║ ╚═╝ ██║    ██║  ██║    ██║  ██║    ██║    ╚██████╔╝\n" + LEFT_MARGIN
            + "╚═╝     ╚═╝    ╚═╝  ╚═╝    ╚═╝  ╚═╝    ╚═╝     ╚═════╝ \n";
    private static final String BORDER = LEFT_MARGIN + "_______________________________________________________\n";

    // Messages
    private static final String MESSAGE_WELCOME = "It's a-me, Mario! how can I help you today?";
    private static final String MESSAGE_EXIT = "Hey! Come back here! You big-a monkey!";
    private static final String MESSAGE_EMPTY = "What do you expect me to say when you didn't save any tasks?";
    private static final String MESSAGE_TASKS = "As you wish, here's what you gotta do:";
    private static final String MESSAGE_DONE = "You did it! Good job little guy!";
    private static final String MESSAGE_ADD = "Got it! I've added this task:";
    private static final String MESSAGE_COUNT = "Now you have %s tasks in the list.";
    private static final String MESSAGE_INVALID = "I don't understand anything you just said";
    private static final String MESSAGE_INVALID_ID =
            "That task wasn't even on the list! You can save the princess if you're that free...";
    private static final String MESSAGE_TASK_ID_MISSING = "You didn't give me the task number to work with...";
    private static final String MESSAGE_MISSING_DELIM = "There is no '/' in your task";
    private static final String MESSAGE_MISSING_DATETIME = "Did you casually forget to put in the date/time?";
    private static final String MESSAGE_ALR_DONE = "Do you happen to have short term memory?";
    private static final String MESSAGE_DELETE = "Got it. I removed this task:";
    private static final String MESSAGE_WRONG_FORMAT = "The date format should be in DD-MM-YY and the time in HH:MM";

    // processes the input and generates the output in the correct format.
    private static String displayOutput(String input) {
        return BORDER + LEFT_MARGIN + input + "\n" + BORDER;
    }

    // displays the task list in the correct format
    public static String displayList(ArrayList<Task> inputArr) {
        if (inputArr.size() == 0) {
            return displayOutput(MESSAGE_EMPTY);
        } else {
            StringBuilder out = new StringBuilder(MESSAGE_TASKS).append("\n");
            for (short i = 0; i < inputArr.size(); i++) {
                out.append(LEFT_MARGIN_DOUBLE).append(i + 1)
                        .append(".").append(inputArr.get(i)).append("\n");
            }
            return displayOutput(out.substring(0, out.length() - 1));
        }
    }

    // adds task to list
    public static String addToList(ArrayList<Task> taskList, TaskType taskType, String taskStr)
            throws BlankTaskException, MissingDelimiterException, MissingDateTimeException, DateTimeParseException {
        Task inputTask;
        LocalDate date = null;
        LocalTime time = null;
        int delimiter = taskStr.indexOf("/");
        if (taskType != TaskType.T) {
            if (delimiter == -1) {
                throw new MissingDelimiterException(MESSAGE_MISSING_DELIM);
            }
            if (delimiter + 3 > taskStr.length()) {
                throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
            }
            String[] datetime = taskStr.substring(delimiter + 3).trim().split(" ");
            date = LocalDate.parse(
                    datetime[0], DateTimeFormatter.ofPattern("dd-MM-yy"));
            if (datetime.length == 2) {
                time = LocalTime.parse(
                        datetime[1], DateTimeFormatter.ofPattern("HH:mm"));
            }
        }
        switch (taskType) {
        case D:
            inputTask = new Deadline(taskStr.substring(0, delimiter), date, time);
            break;
        case E:
            inputTask = new Event(taskStr.substring(0, delimiter), date, time);
            break;
        default:
            inputTask = new ToDo(taskStr);
            break;
        }
        taskList.add(inputTask);
        return displayOutput(MESSAGE_ADD + "\n" + LEFT_MARGIN_DOUBLE + inputTask + "\n"
                + LEFT_MARGIN + String.format(MESSAGE_COUNT, taskList.size()));
    }

    // mark task as done
    public static String markAsDone(ArrayList<Task> taskList, short id) {
        Task curr;
        try {
            curr = taskList.get(id - 1);
        } catch (IndexOutOfBoundsException e) { //change
            return displayOutput(MESSAGE_INVALID_ID);
        }
        if (curr.isDone()) {
            return displayOutput(MESSAGE_ALR_DONE);
        } else {
            curr.markAsDone();
            return displayOutput(MESSAGE_DONE + "\n" + LEFT_MARGIN_DOUBLE + curr);
        }
    }

    public static String delete(ArrayList<Task> taskList, short id) {
        Task curr;
        try {
            curr = taskList.get(id - 1);
        } catch (IndexOutOfBoundsException e) { //change
            return displayOutput(MESSAGE_INVALID_ID);
        }
        taskList.remove(id - 1);
        return displayOutput(MESSAGE_DELETE + "\n" + LEFT_MARGIN_DOUBLE + curr + "\n"
                + LEFT_MARGIN + String.format(MESSAGE_COUNT, taskList.size()));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        ArrayList<Task> taskList = new ArrayList<>();
        HashMap<String, TaskType> taskTypeMap = new HashMap<>(Map.of(
                "event", TaskType.E,
                "deadline", TaskType.D,
                "todo", TaskType.T));
        System.out.print(BORDER + LOGO + displayOutput(MESSAGE_WELCOME));
        while (!terminate) {
            String input = sc.next();
            switch (input) {
            case "bye":
                System.out.print(displayOutput(MESSAGE_EXIT));
                terminate = true;
                break;
            case "list":
                System.out.print(displayList(taskList));
                sc.nextLine();
                break;
            case "done":
                short taskId;
                try {
                    taskId = Short.parseShort(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.print(displayOutput(MESSAGE_TASK_ID_MISSING));
                    break;
                }
                System.out.print(markAsDone(taskList, taskId));
                break;
            case "delete":
                try {
                    taskId = Short.parseShort(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.print(displayOutput(MESSAGE_TASK_ID_MISSING));
                    break;
                }
                System.out.print(delete(taskList, taskId));
                break;
            default:
                TaskType currType = taskTypeMap.get(input);
                if (currType == null) {
                    System.out.print(displayOutput(MESSAGE_INVALID));
                    sc.nextLine();
                    break;
                }
                try {
                    System.out.print(addToList(taskList, currType, sc.nextLine()));
                } catch (BlankTaskException | MissingDelimiterException | MissingDateTimeException e) {
                    System.out.print(displayOutput(e.getMessage()));
                } catch (DateTimeParseException e) {
                    System.out.print(displayOutput(MESSAGE_WRONG_FORMAT));
                }
                break;
            }
        }
    }
}
