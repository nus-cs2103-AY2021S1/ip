import java.util.Scanner;

public class Duke {

    // General style properties of the chatbot.
    private static final String LEFT_MARGIN = "    ";
    private static final String LEFT_MARGIN_DOUBLE = LEFT_MARGIN + LEFT_MARGIN;
    private static final String LOGO =
            "\n" + LEFT_MARGIN + "███╗   ███╗     █████╗     ██████╗     ██╗     ██████╗ \n" + LEFT_MARGIN
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
    public static final String MESSAGE_DONE = "You did it! Good job little guy!";
    public static final String MESSAGE_INVALID = "I don't understand anything you just said";
    public static final String MESSAGE_ADD = "Got it! I've added this task:";
    public static final String MESSAGE_COUNT = "Now you have %s tasks in the list.";

    // processes the input and generates the output in the correct format.
    public static String displayOutput(String input) {
        return BORDER + LEFT_MARGIN + input + "\n" + BORDER;
    }

    // displays the task list in the correct format
    public static String displayList(Task[] inputArr) {
        if (Task.getCount() == 0) {
            return displayOutput(MESSAGE_EMPTY);
        } else {
            StringBuilder out = new StringBuilder(MESSAGE_TASKS).append("\n");
            for (short i = 1; i <= Task.getCount(); i++) {
                out.append(LEFT_MARGIN_DOUBLE).append(inputArr[i].getId())
                        .append(".").append(inputArr[i]).append("\n");
            }
            return displayOutput(out.substring(0, out.length() - 1));
        }
    }

    // adds task to list
    public static String addToList(Task[] taskList, char taskType, String taskStr) {
        Task inputTask;
        int delimiter = taskStr.indexOf("/");
        switch (taskType) {
        case 'D':
            inputTask = new Deadline(taskStr.substring(0, delimiter), taskStr.substring(delimiter + 4));
            break;
        case 'E':
            inputTask = new Event(taskStr.substring(0, delimiter), taskStr.substring(delimiter + 4));
            break;
        default:
            inputTask = new ToDo(taskStr);
            break;
        }
        taskList[inputTask.getId()] = inputTask;
        return displayOutput(MESSAGE_ADD + "\n" + LEFT_MARGIN_DOUBLE + inputTask + "\n"
                + LEFT_MARGIN + String.format(MESSAGE_COUNT, Task.getCount()));
    }

    // mark task as done
    public static String markAsDone(Task[] taskList, short id) {
        taskList[id].markAsDone();
        return displayOutput(MESSAGE_DONE + "\n" + LEFT_MARGIN_DOUBLE + taskList[id]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        Task[] taskList = new Task[100];
        System.out.print(BORDER + LOGO + displayOutput(MESSAGE_WELCOME));
        while (!terminate) {
            switch (sc.next()) {
            case "bye":
                System.out.print(displayOutput(MESSAGE_EXIT));
                terminate = true;
                break;
            case "list":
                System.out.print(displayList(taskList));
                break;
            case "done":
                System.out.print(markAsDone(taskList, sc.nextShort()));
                break;
            case "todo":
                System.out.print(addToList(taskList, 'T', sc.nextLine()));
                break;
            case "event":
                System.out.print(addToList(taskList, 'E', sc.nextLine()));
                break;
            case "deadline":
                System.out.print(addToList(taskList, 'D', sc.nextLine()));
                break;
            default:
                sc.nextLine();
                System.out.print(displayOutput(MESSAGE_INVALID));
                break;
            }
        }
    }
}
