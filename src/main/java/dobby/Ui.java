package dobby;

import java.util.Scanner;

/**
 * Interacts with the user
 */
public class Ui {
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Calls reply function with greeting string
     */
    public static String greet() {
        String message = "Hello! I'm Dobby. \nHow can I help you!";
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
