import java.util.Scanner;

/**
 * The Best2013TBot program that implements a chatbot.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Duke {

    private static String indent = "   ";

    /**
     * This method greets the user.
     */
    public static void greet() {
        System.out.println(indent + "----------------------------");
        System.out.println(indent + "Hello! I'm Best2103/TBot\n"+ indent + "What can I do for you?");
        System.out.println(indent + "----------------------------");
    }

    /**
     * This method respond to the user.
     */
    public static void respond() {
        Scanner userInput = new Scanner(System.in);
        while(true) {
            String input = userInput.nextLine();
            System.out.println(indent + "----------------------------");
            if (input.trim().equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(indent + "----------------------------");
                break;
            }
            System.out.println(indent + input);
            System.out.println(indent + "----------------------------");
        }
    }

    public static void main(String[] args) {
        greet();
        respond();
    }
}
