package duke.echo;

import java.util.Scanner;

/**
 * Encapsulates an Echo class that repeats commands from the user.
 */
public class Echo {
    /**
     * Whether Mrs Dino should terminate.
     */
    private static boolean terminate = false;

    /**
     * Initialises the program to echo user inputs.
     */
    public static void initialise() {
        while (!Echo.terminate) {
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            String command = sc.nextLine();  // Read user input
            Echo.echoMessage(command); // Output user input
        }
    }

    /**
     * Reads user input and outputs them exactly as how the user typed.
     *
     * @param message Message user typed.
     */
    public static void echoMessage(String message) {
        if (!message.equals("bye")) {
            System.out.println(message);
        } else {
            Echo.terminate = true;
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
