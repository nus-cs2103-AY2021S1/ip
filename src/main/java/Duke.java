import java.util.Scanner;

public class Duke {

    /*
     * Prints Duke's welcome message
     */
    private static void welcome() {
        printOutput("     Hello! I'm Duke\n" +
                        "     What can I do for you?");
    }

    /*
     * Wraps input in dashes
     */
    private static void printOutput(String input) {
        System.out.println("    ____________________________________________________________\n" +
                input + "\n" +
                "    ____________________________________________________________");
    }

    /*
     * Handles input, and calls corresponding functions
     * @return: returns boolean of whether or not to continue
     */
    private static boolean inputHandler(String input) {
        switch(input) {
            case "bye":
                exit();
                return false;
            default:
                printOutput("     " + input);
                return true;
        }
    }

    /*
     * Does all necessary actions before exiting
     */
    private static void exit() {
        printOutput("     Bye. Hope to see you again soon!");
    }

    /*
     * reads user input, and does corresponding action
     */
    public static void main(String[] args) {
        welcome();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) {
            boolean toContinue = inputHandler(input);
            if (toContinue) {
                input = scanner.nextLine();
            } else {
                break;
            }
        }
    }
}








