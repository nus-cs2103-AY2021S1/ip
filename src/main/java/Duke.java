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
    private static final String MESSAGE_WELCOME = "It's a-me, Mario! how can I help you today?";
    private static final String MESSAGE_EXIT = "Hey! Come back here! You big-a monkey!";
    private static final String MESSAGE_EMPTY = "What do you expect me to say when you didn't save any tasks?";
    private static final String MESSAGE_TASKS = "As you wish, here's what you gotta do:";
    public static final String MESSAGE_DONE = "You did it! Good job little guy!";

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
            return displayOutput(out.substring(0, out.length() - 2));
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
        return displayOutput(MESSAGE_DONE + "\n" + LEFT_MARGIN_DOUBLE + taskList[id]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        System.out.print(BORDER + LOGO + displayOutput(MESSAGE_WELCOME));
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
