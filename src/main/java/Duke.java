import java.util.Scanner;

/** Duke is a chatbot that allows users to send input to perform tasks.
 */
public class Duke {
    public static void main(String[] args) {
        // Introduction messages
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Initialise memory
        int memorySize = 100;
        String[] memory = new String[memorySize];
        int memoryLength = 0;

        // Main conversation loop
        Scanner sc = new Scanner(System.in);
        boolean isSpeaking = true;
        while (isSpeaking) {

            // Get user input
            String userInput = sc.nextLine();

            switch(userInput) {

                // Exit the program
                case "bye":
                    isSpeaking = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                // List the tasks available in memory
                case "list":
                    for (int i = 0; i < memoryLength; i++) {
                        System.out.println(i + 1 + ". " + memory[i]);
                    }
                    break;

                // For all other user input, add to memory list
                default:
                    memory[memoryLength] = userInput;
                    memoryLength++;
                    System.out.println("added: " + userInput);
            }
        }
    }
}
