import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you today? (type: \"help\" to view list of commands)\n" +
                "=========================================================================");

        Scanner scanner = new Scanner(System.in);
        boolean terminated = false;

        while (!terminated) {
            String userInput = scanner.next();
            if (userInput.equals("bye")) {
                terminated = true;
                System.out.println("Duke says: Goodbye and have a nice day! :D");
                scanner.close();
            } else if (userInput.equals("help")) {
                System.out.println("bye: terminates program");
            } else {
                System.out.println("Duke says: " + userInput);
            }
            System.out.println("=========================================================================");
        }
    }
}

