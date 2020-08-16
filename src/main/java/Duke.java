import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction messages
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Repeat user input until user inputs "bye"
        Scanner sc = new Scanner(System.in);
        boolean isSpeaking = true;
        while (isSpeaking) {

            // Read user input
            String userInput = sc.nextLine();

            // Exit if user enters 'bye', else repeat user's input
            if (userInput.equals("bye")) {
                isSpeaking = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(userInput);
            }
        }
    }
}
