import java.util.Scanner;

public class Duke {
    private final static String SEPARATOR = "___________________________________________________________";

    private static void printMessage(String message)  {
        System.out.println("     " + SEPARATOR);
        System.out.println("     " + message);
        System.out.println("     " + SEPARATOR);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                String BYE_STATEMENT = "Bye. Hope to see you again soon!";
                printMessage(BYE_STATEMENT);
                break;
            } else {
                printMessage(command);
            }
        }
    }
}
