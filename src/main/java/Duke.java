import java.util.Scanner;
import java.util.ArrayList;

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
        prettyPrint("Hello! I'm Duke\n" +  "     What can I do for you?");
    }

    // Driver method to respond to user input
    private static void respondPicker() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> userInputCollector = new ArrayList();

        greet();

        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                prettyPrint(userInputCollector);
            } else if (userInput.split(" ", 2)[0].equals("done")) {
                System.out.println("here");
                Task taskToUpdate = userInputCollector.get(Integer.parseInt(userInput.split(" ", 2)[1])
                        - 1);
                taskToUpdate.updateStatus(true);
                prettyPrint("Nice! I've marked this task as done: \n" + "       " + taskToUpdate);
            }
            else {
                prettyPrint(userInput);
                userInputCollector.add(new Task(userInput));
            }

            userInput = scan.nextLine();
        }

        prettyPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the given string with additional wrappings
     *
     * @param string String to print
     */
    private static void prettyPrint(String string) {
        System.out.println(LINEDIVIDER + "     " + string + "\n" + LINEDIVIDER);
    }

    /**
     * Prints the given array list with additional effects
     *
     * @param array Array of strings to print
     */
    private static void prettyPrint(ArrayList array) {
        System.out.println(LINEDIVIDER);
        for (int i = 0; i < array.size(); i++) {
            System.out.println("     " + (i + 1) + "." + array.get(i));
        }
        System.out.println(LINEDIVIDER);
    }
}
