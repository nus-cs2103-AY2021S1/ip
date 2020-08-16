import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETING = "Hello, I'm Duke\nwhat can I do for you?\n";
    private static final String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String CIAO = "Spero di rivederti presto";
    private static final String CONVO_START = DIVIDER + LOGO + "\n" + GREETING + DIVIDER;

    public static void main(String[] args) {

        System.out.println(CONVO_START);

        readInput(new Scanner(System.in));

    }

    private static void readInput(Scanner sc) {
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(DIVIDER + CIAO + "\n" + DIVIDER);
                return;
            } else {
                System.out.println(DIVIDER + input + "\n" + DIVIDER);
            }
        }
    }
}
