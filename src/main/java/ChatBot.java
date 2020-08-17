// LEVEL 2

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


// The chat bot class to handle the internal logic
public class ChatBot {
    List<String> list = new LinkedList<>();

    // The entry point to run the chat bot
    public void start() {
        String welcome = "Hello! I'm Duke \n    What can I do for you?";
        sendChat(welcome);
        String response = receiveChat();
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                printList();
            } else {
                sendChat("Added: " + response);
                list.add(response);
            }
            response = receiveChat();
        }
        sendChat("Bye. Hope to see you again soon!");
    }

    // Return the list of tasks
    private void printList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("    " + i + ". " + list.get(i - 1));
        }
        System.out.println("    ____________________________________________________________");
    }

    // Send specified content in a chat box
    private void sendChat(String content) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + content);
        System.out.println("    ____________________________________________________________");
    }

    // Scan for user's response and return the string
    private String receiveChat() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
