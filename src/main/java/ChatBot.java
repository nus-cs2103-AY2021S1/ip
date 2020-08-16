import java.util.Scanner;

public class ChatBot {

    public void start() {
        String welcome = "Hello! I'm Duke \nWhat can I do for you?";
        sendChat(welcome);
        String response = receiveChat();
        while (!response.equals("bye")) {
            sendChat(response);
            response = receiveChat();
        }
        sendChat("Bye. Hope to see you again soon!");
    }

    private void sendChat(String content) {
        System.out.println("____________________________________________________________");
        System.out.println(content);
        System.out.println("____________________________________________________________");
    }

    private String receiveChat() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
