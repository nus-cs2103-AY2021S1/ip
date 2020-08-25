package duke;

import java.util.Scanner;

public class Echo {
    private static boolean terminate = false;
    public static void initialise() {
        while (!Echo.terminate) {
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
             String command = sc.nextLine();  // Read user input
            Echo.echoMessage(command); // Output user input
        }
    }

    public static void echoMessage(String message) {
        if (!message.equals("bye")) {
            System.out.println(message);
        } else {
            Echo.terminate = true;
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
