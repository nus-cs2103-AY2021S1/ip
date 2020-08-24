package duke;

import java.util.Scanner;

/**
 * Represents the text ui interface
 */
public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Read user commands
     *
     * @return user command
     */
    public String readCommand() {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }

    /**
     * Prints chatbot response
     *
     * @param message chatbot response
     */
    public void print(String message) {
        System.out.print("     ");
        System.out.println(message);
    }

    /**
     * Build line separator
     */
    public void buildChatFence() {
        print("----------------------------------------");
    }

    /**
     * Show welcome message
     */
    public void showWelcomeMessage() {
        buildChatFence();
        print("Hellowww!! I'm Alexa, your personal todo manager!");
        print("How can I help you today?");
        buildChatFence();
    }

    /**
     * Show close message
     */
    public void showCloseMessage() {
        print("Bye? I hope it's not forever! Come back soon!");
    }

    /**
     * Show message when user adds a new todo, deadline or event
     *
     * @param message string to print
     * @param size    task list current size
     */
    public void printAddConfirmation(String message, int size) {
        print("Got it. I've added this task:");
        print(message);
        print(String.format("Now you have %d %s in the list", size, size > 1 ? "tasks" : "task"));
    }
}
