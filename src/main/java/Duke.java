import java.util.Scanner;

public class Duke {
    private static final String LINEDIVIDER = "    ____________________________________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        respondPicker();
    }

    // Prints out the greeting
    private static void greet() {
        System.out.println(prettyPrint("Hello! I'm Duke\n" +  "     What can I do for you?"));
    }

    // Driver method to respond to user input
    private static void respondPicker() {
        Scanner scan = new Scanner(System.in);

        greet();

        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(prettyPrint(userInput));
            userInput = scan.nextLine();
        }

        System.out.println(prettyPrint("Bye. Hope to see you again soon!"));
    }

    /**
     * Returns the given string with additional wrappings
     *
     * @param string
     * @return formatted string
     */
    private static String prettyPrint(String string) {
        return LINEDIVIDER + "     " + string + "\n" + LINEDIVIDER;
    }
}
