import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatbot = "Bob: ";
        String skipLine = "\n";
        String user = skipLine + "You: ";
        List<String> listOfCommands = new ArrayList<>();

        // Greetings
        System.out.println(chatbot + "Hey there! I'm Bob" + skipLine + "What can I do for you today?");
        System.out.println(user);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoUser = sc.nextLine();

            boolean exit = echoUser.equals("bye");

            // Exit chatbot
            if (exit) {
                break;
            }

            boolean showListOfCommands = echoUser.equals("list");

            // Display List
            if (showListOfCommands) {
                System.out.println(skipLine + chatbot);

                if (listOfCommands.size() == 0) {
                    System.out.println("List is empty :(");
                }

                for (String command: listOfCommands) {
                    int listIndex = listOfCommands.indexOf(command) + 1;
                    System.out.println(listIndex + ". " + command);
                }

            // Add text to the list
            } else {
                listOfCommands.add(echoUser);

                System.out.println();
                System.out.println(chatbot + "added '" + echoUser + "'");
            }

            // Prompt user commands
            System.out.println(user);

        }

        sc.close();
        System.out.println(skipLine + chatbot + "Goodbye! Have a nice day :D");

    }
}
