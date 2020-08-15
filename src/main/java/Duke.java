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


    // processes the input and generates the output. In this implementation, it just repeats the input in another
    // format.
    public static String commandOutput(String input) {
        return DIVIDER + LEFT_MARGIN + input + "\n" + DIVIDER;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(DIVIDER + LOGO + commandOutput(WELCOME_MESSAGE));
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.print(commandOutput(EXIT_MESSAGE));
                break;
            } else {
                System.out.print(commandOutput(input));
            }
        }
    }
}
