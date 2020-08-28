package dobby;

import java.util.Scanner;

public class Ui {
    // String for output format
    private static final String UNDERSCORE = "_________________________________________" +
            "______________________________________________";
    static Scanner SC = new Scanner(System.in);

    private static final String ALL_COMMANDS = "\n    You can use the following commands in this chat bot:"
            + (Commands.TODO).getUsage()
            + (Commands.DEADLINE).getUsage()
            + (Commands.EVENT).getUsage()
            + (Commands.LIST).getUsage()
            + (Commands.DONE).getUsage()
            + (Commands.DELETE).getUsage()
            + (Commands.SCHEDULED).getUsage()
            + (Commands.FIND).getUsage()
            + (Commands.FINDTYPE).getUsage()
            + (Commands.BYE).getUsage();

    /**
     * Calls reply function with greeting string
     */
    public static void greet () {
        reply("\n    Hello! I'm Dobby" + ALL_COMMANDS + "\n    How can I help you?\n    ");
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
        System.out.println("    " + UNDERSCORE + message + UNDERSCORE);
    }
}
