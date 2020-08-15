import java.util.Scanner;

public class Duke {

    private static void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    private static void handleCommands() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                printResponse("Bye. Hope to see you again soon!");
                break;
            } else {
                printResponse(userCommand);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Hello! I'm Duke\nWhat can I do for you?");
        handleCommands();

    }
}
