import java.util.ArrayList;
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

    // formats a list item
    public static String displayListItem(String listItem, short rank) {
        return String.format("%s. %s", rank, listItem);
    }

    // displays the task list in the correct format
    public static String displayList(ArrayList<String> inputArr) {
        StringBuilder out = new StringBuilder(DIVIDER);
        for (short i = 0; i < inputArr.size(); i++) {
            out.append(LEFT_MARGIN).append(displayListItem(inputArr.get(i), (short) (i + 1))).append("\n");
        }
        return out.append(DIVIDER).toString();
    }

    // adds task to list
    public static String addToList(String inputTask, ArrayList<String> taskList) {
        taskList.add(inputTask);
        return displayOutput("added: " + inputTask);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
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
