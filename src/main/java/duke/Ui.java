package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }

    public void print(String message) {
        System.out.print("     ");
        System.out.println(message);
    }

    public void buildChatFence() {
        print("----------------------------------------");
    }

    public void showWelcomeMessage() {
        buildChatFence();
        print("Hellowww!! I'm Alexa, your personal todo manager!");
        print("How can I help you today?");
        buildChatFence();
    }

    public void showCloseMessage() {
        print("Bye? I hope it's not forever! Come back soon!");
    }

    public void printAddConfirmation(String message, int size) {
        print("Got it. I've added this task:");
        print(message);
        print(String.format("Now you have %d %s in the list", size, size > 1 ? "tasks" : "task"));
    }
}
