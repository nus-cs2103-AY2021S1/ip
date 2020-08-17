// LEVEL 1

import java.util.Scanner;

public class ChatBot {

    // The entry point to run the chat bot
    public void start() {
        String welcome = "Hello! I'm Duke \n    What can I do for you?";
        sendChat(welcome);
        String response = receiveChat();
        while (!response.equals("bye")) {
            sendChat(response);
            response = receiveChat();
        }
        sendChat("Bye. Hope to see you again soon!");
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
