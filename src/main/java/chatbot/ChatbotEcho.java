package chatbot;

import java.io.InputStream;
import java.util.Scanner;

public class ChatbotEcho {

    private final String greeting;
    private final Scanner scanner;
    private final String endingGreeting;

    public ChatbotEcho(InputStream inputStream) {
        this("", inputStream, "");
    }

    public ChatbotEcho(String greeting, InputStream inputStream, String endingGreeting) {
        this.greeting = greeting;
        this.scanner = new Scanner(inputStream);
        this.endingGreeting = endingGreeting;
    }

    public void run() {
        String input;

        System.out.print(greeting + "\n> ");
        while(!(input = scanner.nextLine()).equals("bye")) {
            System.out.print("Echo: " + input + "\n> ");
        }
        System.out.println(endingGreeting);
    }

}
