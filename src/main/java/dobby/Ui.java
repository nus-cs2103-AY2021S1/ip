package dobby;

import java.util.Scanner;

/**
 * Interacts with the user
 */
public class Ui {
    private static final String ALL_COMMANDS = "You can use the following commands in this chat bot:"
            + "\n  " + (Commands.TODO).getUsage()
            + "\n  " + (Commands.DEADLINE).getUsage()
            + "\n  " + (Commands.EVENT).getUsage()
            + "\n  " + (Commands.LIST).getUsage()
            + "\n  " + (Commands.DONE).getUsage()
            + "\n  " + (Commands.DELETE).getUsage()
            + "\n  " + (Commands.SCHEDULED).getUsage()
            + "\n  " + (Commands.FIND).getUsage()
            + "\n  " + (Commands.FINDTYPE).getUsage()
            + "\n  " + (Commands.BYE).getUsage();
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Calls reply function with greeting string
     */
    public static String greet() {
        String message = "Hello! I'm Dobby.\n" + ALL_COMMANDS;
        reply(message);
        return message;
    }

    /**
     * Returns string which is entered by user
     * @return String user input
     */
    public static String getInput() {
        String text = SC.nextLine();
        if (text.equals("bye")) {
            SC.close();
        }
        return text;
    }

    /**
     * Prints reply message
     */
    public static void reply(String message) {
        System.out.println(message + "\n");
    }
}
