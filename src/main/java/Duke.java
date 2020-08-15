import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<String> messages;

    // Constructor for the bot
    public Duke(ArrayList<String> messages) {
        this.messages = messages;
    }

    // Method to intiate the bot
    private static void startBot() {
        String welcome = "Hello I am Duke!\nHow can I help you?\n";
        System.out.println(welcome);

        Duke newBot = new Duke(new ArrayList<String>());

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String message = sc.next();
            message += sc.nextLine();
            if (message.equals("bye")) {
                System.out.println("\nBye! Have a nice day!\n");
                break;
            } else if (message.equals("list")){
                if (newBot.messages.isEmpty()) {
                    System.out.println("\nThere are currently no messages stored!\n");
                } else {
                    newBot.displayMessages();
                }
            } else {
                newBot.addMessages(message);
            }
        }
    }

    // Method to add new messages to the bot
    private void addMessages(String message) {
        this.messages.add(message);
        System.out.println("\nadded: " + message + "\n");
    }

    // Method to display all messages
    private void displayMessages() {
        int index = 1;
        System.out.println("\n");
        for (String text: this.messages) {
            if (text != null) {
                System.out.println(index + ". " + text);
                index++;
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        startBot();
    }
}