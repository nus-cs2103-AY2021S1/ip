import java.util.Scanner;

public class Duke {

    // General style properties of the chatbot.
    private static final String LEFT_MARGIN = "    ";
    private static final String LOGO =
            "\n" + LEFT_MARGIN + "███╗   ███╗     █████╗     ██████╗     ██╗     ██████╗ \n"
                    + LEFT_MARGIN + "████╗ ████║    ██╔══██╗    ██╔══██╗    ██║    ██╔═══██╗\n"
                    + LEFT_MARGIN + "██╔████╔██║    ███████║    ██████╔╝    ██║    ██║   ██║\n"
                    + LEFT_MARGIN + "██║╚██╔╝██║    ██╔══██║    ██╔══██╗    ██║    ██║   ██║\n"
                    + LEFT_MARGIN + "██║ ╚═╝ ██║    ██║  ██║    ██║  ██║    ██║    ╚██████╔╝\n"
                    + LEFT_MARGIN + "╚═╝     ╚═╝    ╚═╝  ╚═╝    ╚═╝  ╚═╝    ╚═╝     ╚═════╝ \n";
    private static final String WELCOME_MESSAGE = "It's a-me, Mario! how can I help you today?\n";
    private static final String EXIT_MESSAGE = "Hey! Come back here! You big-a monkey!\n";
    private static final String DIVIDER =
            LEFT_MARGIN + "_______________________________________________________\n";


    // processes the input and generates the output in the correct format.
    public static String displayOutput(String input) {
        return DIVIDER + LEFT_MARGIN + input + "\n" + DIVIDER;
    }

    // displays the task list in the correct format
    public static String displayList(Task[] inputArr) {
        if (Task.getCount() == 0) {
            return displayOutput("What do you expect me to say when you didn't save any tasks?");
        } else {
            StringBuilder out = new StringBuilder(DIVIDER);
            for (short i = 1; i <= Task.getCount(); i++) {
                out.append(LEFT_MARGIN).append(inputArr[i]).append("\n");
            }
            return out.append(DIVIDER).toString();
        }
    }

    // adds task to list
    public static String addToList(String input, Task[] taskList) {
        Task inputTask = new Task(input);
        taskList[inputTask.getId()] = inputTask;
        return displayOutput("added: " + inputTask.getName());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        System.out.println(DIVIDER + LOGO + displayOutput(WELCOME_MESSAGE));
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.print(displayOutput(EXIT_MESSAGE));
                break;
            } else if (input.equals("list")) {
                System.out.print(displayList(taskList));
            } else {
                System.out.print(addToList(input, taskList));
            }
        }
    }
}
