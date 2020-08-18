import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // Initialise strings to separate messages from Duke
        // and commands from CLI
        String servantSpeak = "Duke:\n";
        String masterSpeak = "Your Command Sire:";

        // Introduction at the beginning of the chat
        System.out.println(servantSpeak
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");

        // Initialise the Scanner object to get input from user
        Scanner myObj = new Scanner(System.in);
        String input;

        // Initialise ArrayList to store input from user
        ArrayList<String> userTexts = new ArrayList<String>();

        // Start chat
        do {
            // Get input from user
            System.out.println(masterSpeak);
            input = myObj.nextLine();
            System.out.println();

            // If user inputs "bye" in any case, end the chat
            if (input.equals("bye")) {
                System.out.println(servantSpeak
                        + "    It was a pleasure to serve you my Liege.\n"
                        + "    Till next time.");
                break;
            }

            // If user requests for list, display list
            if (input.equals("list")) {
                int count = 1;
                System.out.print(servantSpeak);
                for (String i : userTexts) {
                    System.out.println("    "
                            + count + ". "
                            + i);
                    count++;
                }
                System.out.println();
                continue;
            }

            // Default: Add input from user into the ArrayList
            userTexts.add(input);
            System.out.println(servantSpeak
                    + "    added: " + input + "\n");
        } while (!input.equalsIgnoreCase("bye"));

    }
}
