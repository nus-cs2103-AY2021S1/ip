import java.util.Scanner;

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

        // Start chat
        do {
            System.out.println(masterSpeak);
            input = myObj.nextLine();
            System.out.println();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(servantSpeak
                        + "    It was a pleasure to serve you my Liege.\n"
                        + "    Till next time.");
                break;
            }
            System.out.println(servantSpeak
                    + "    " + input + "\n");
        } while (!input.equalsIgnoreCase("bye"));

    }
}
