import java.util.Scanner;

public class Duke {
    private final static String INDENT = "    ";
    private final static String horizL = INDENT +
            "____________________________________________________________";
    private final static String logo = INDENT + " ____        _        \n"
            + INDENT + "|  _ \\ _   _| | _____ \n"
            + INDENT + "| | | | | | | |/ / _ \\\n"
            + INDENT + "| |_| | |_| |   <  __/\n"
            + INDENT + "|____/ \\__,_|_|\\_\\___|\n";

    private static void intro() {
        System.out.println(horizL + "\n" + logo + "\n" +
                INDENT + "Hello. I'm Duke.\n" +
                INDENT + "How can I help you?\n" + horizL);
    }

    private static void bye() {
        System.out.println("\n" + INDENT +
                "Bye! Hope to see you again soon!\n" + horizL);
    }

    public static void main(String[] args) {
        Duke.intro();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals("bye")) {
                System.out.print(horizL);
                break;
            } else {
                System.out.println(horizL);
                System.out.println(INDENT + input);
                System.out.println(horizL);
            }
        }
        Duke.bye();
    }
}
