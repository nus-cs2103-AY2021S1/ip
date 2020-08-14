import java.util.Scanner;

public class Duke {
    private final Scanner sc;
    private final String SEPERATOR = "    ____________________________________"
        + "________________________\n";
    private final String EXIT_COMMAND = "bye";
    private String command;

    public Duke() {
        sc = new Scanner(System.in);
    }

    private void printGreeting() {
        System.out.printf("%s     Hello! I'm Duke\n"
                + "     What can I do for you?\n%s", SEPERATOR, SEPERATOR);
    }

    private void getCommand() {
        System.out.println();
        command = sc.nextLine().trim();
        System.out.printf("%s", SEPERATOR);
    }

    private void echoCommand() {
        System.out.printf("     %s\n%s", command, SEPERATOR);
    }

    private void printExit() {
        System.out.printf("     Bye. Hope to see you again soon!\n%s",
                SEPERATOR);
    }

    public void initialise() {
        printGreeting();

        while (true) {
            getCommand();
            if (command.equals(EXIT_COMMAND)) break;
            echoCommand();
        }

        printExit();
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        new Duke().initialise();
    }
}