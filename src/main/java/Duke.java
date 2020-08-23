import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
            throws BlankTaskException, MissingDelimiterException, MissingDateTimeException {
        Task inputTask;
        int delimiter = taskStr.indexOf("/");
        if (taskType != TaskType.T && delimiter == -1) {
            throw new MissingDelimiterException(MESSAGE_MISSING_DELIM);
        }
        if (taskType != TaskType.T && delimiter + 3 > taskStr.length()) {
            throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
        }
        switch (taskType) {
        case D:
            inputTask = new Deadline(taskStr.substring(0, delimiter), taskStr.substring(delimiter + 3));
            break;
        case E:
            inputTask = new Event(taskStr.substring(0, delimiter), taskStr.substring(delimiter + 3));
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
        ArrayList<Task> taskList = new ArrayList<>();
        loadFromMemory(taskList);
        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
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
                updateMemory(taskList);
                break;
            case "delete":
                try {
                    taskId = Short.parseShort(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.print(displayOutput(MESSAGE_TASK_ID_MISSING));
                    break;
                }
                System.out.print(delete(taskList, taskId));
                updateMemory(taskList);
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
                }
                updateMemory(taskList);
                break;
            }
        }
    }

    private static void updateMemory(ArrayList<Task> taskList) {
        Path dataPath = Paths.get("data", "duke.txt");
        StringBuilder taskListString = new StringBuilder();
        for (Task task : taskList
        ) {
            taskListString.append(task.toString().replaceAll("[)\\[\\](✓✗]", "")
                    .replaceAll("by:|at:", "/--")).append("\n").append(task.isDone()).append("\n");
        }
        try {
            Files.writeString(dataPath, taskListString, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadFromMemory(ArrayList<Task> taskList) {
        Path dataPath = Paths.get("data", "duke.txt");
        if (Files.notExists(dataPath)) {
            Path dataDir = Paths.get("data");
            if (Files.notExists(dataDir)) {
                try {
                    Files.createDirectory(dataDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.createFile(dataPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Scanner data;
            try {
                data = new Scanner(dataPath);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (data.hasNext()) {
                try {
                    addToList(taskList, TaskType.valueOf(data.next()), data.nextLine());
                } catch (BlankTaskException | MissingDelimiterException | MissingDateTimeException e) {
                    e.printStackTrace();
                }
                if (data.nextBoolean()) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
        }
    }
}
