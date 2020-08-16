import java.util.Scanner;

public class Duke {

    // General style properties of the chatbot.
    private static final String LEFT_MARGIN = "    ";
    private static final String LEFT_MARGIN_DOUBLE = LEFT_MARGIN + LEFT_MARGIN;
    private static final String LOGO =
            "\n" + LEFT_MARGIN + "███╗   ███╗     █████╗     ██████╗     ██╗     ██████╗ \n"
                    + LEFT_MARGIN + "████╗ ████║    ██╔══██╗    ██╔══██╗    ██║    ██╔═══██╗\n"
                    + LEFT_MARGIN + "██╔████╔██║    ███████║    ██████╔╝    ██║    ██║   ██║\n"
                    + LEFT_MARGIN + "██║╚██╔╝██║    ██╔══██║    ██╔══██╗    ██║    ██║   ██║\n"
                    + LEFT_MARGIN + "██║ ╚═╝ ██║    ██║  ██║    ██║  ██║    ██║    ╚██████╔╝\n"
                    + LEFT_MARGIN + "╚═╝     ╚═╝    ╚═╝  ╚═╝    ╚═╝  ╚═╝    ╚═╝     ╚═════╝ \n";
    private static final String BORDER =
            LEFT_MARGIN + "_______________________________________________________\n";

    // Messages
    private static final String MESSAGE_WELCOME = "It's a-me, Mario! how can I help you today?\n";
    private static final String MESSAGE_EXIT = "Hey! Come back here! You big-a monkey!\n";
    private static final String MESSAGE_EMPTY = "What do you expect me to say when you didn't save any tasks?";
    private static final String MESSAGE_TASKS = "As you wish, here's what you gotta do:\n";
    public static final String MESSAGE_DONE = "You did it! Good job little guy!\n";

    // processes the input and generates the output in the correct format.
    public static String displayOutput(String input) {
        return BORDER + LEFT_MARGIN + input + "\n" + BORDER;
    }

    // displays the task in the correct style (without the numbering)
    public static String displayTask(Task task) {
        return (task.isDone() ? "[✓] " : "[✗] ") + task.getName() + "\n";
    }

    // displays the task list in the correct format
    public static String displayList(Task[] inputArr) {
        if (Task.getCount() == 0) {
            return displayOutput(MESSAGE_EMPTY);
        } else {
            StringBuilder out = new StringBuilder(BORDER).append(LEFT_MARGIN).append(MESSAGE_TASKS);
            for (short i = 1; i <= Task.getCount(); i++) {
                out.append(LEFT_MARGIN_DOUBLE).append(inputArr[i].getId()).append(".")
                        .append(displayTask(inputArr[i]));
            }
            return out.append(BORDER).toString();
        }
    }

    // adds task to list
    public static String addToList(String input, Task[] taskList) {
        Task inputTask = new Task(input);
        taskList[inputTask.getId()] = inputTask;
        return displayOutput("added: " + inputTask.getName());
    }

    // mark task as done
    public static String markAsDone(Task[] taskList, short id) {
        taskList[id].markAsDone();
        return BORDER + LEFT_MARGIN + MESSAGE_DONE + LEFT_MARGIN_DOUBLE + displayTask(taskList[id]) + BORDER;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        System.out.println(BORDER + LOGO + displayOutput(MESSAGE_WELCOME));
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.print(displayOutput(MESSAGE_EXIT));
                break;
            } else if (input.equals("list")) {
                System.out.print(displayList(taskList));
            } else if (input.startsWith("done")) {
                System.out.print(markAsDone(taskList, Short.parseShort(input.substring(5))));
            } else {
                System.out.print(addToList(input, taskList));
            }
        }
    }
}
