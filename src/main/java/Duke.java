import java.util.Scanner;

public class Duke {
    private final String VERSION_NUMBER = "1.0.0";
    private final Scanner sc = new Scanner(System.in);
    private final String NEW_LINE = "\n";
    private final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    private final String PADDING = "      ";
    private final String EXIT_COMMAND = "BYE";
    private final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;

    public void init() {
        sayHello();

        while(true) {
            if (sc.hasNext()) {
                String input = sc.nextLine().trim();
                if (input.toUpperCase().equals(EXIT_COMMAND)) { // to make command case-insensitive
                    sayGoodbye();
                    break;
                }
                echoBack(input);
            }
        }
    }

    private void echoBack(String message) {
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void sayGoodbye() {
        System.out.printf(MESSAGE_TEMPLATE, "Goodbye, hope to see you again!");
    }

    private void sayHello() {
        String logo =
                  "     _____             _    \n"
                + "    |  __ \\           | |   \n"
                + "    | |  | | ___ _   _| | __\n"
                + "    | |  | |/ _ \\ | | | |/ /\n"
                + "    | |__| |  __/ |_| |   < \n"
                + "    |_____/ \\___|\\__,_|_|\\_\\  v" + VERSION_NUMBER + "\n";
        String intro_message = "I'm Deuk, nice to meet you\n" + PADDING +
                "How can I be of service today?";
        System.out.printf(logo + MESSAGE_TEMPLATE, intro_message);
    }

    public static void main(String[] args) {
        new Duke().init();
    }
}
