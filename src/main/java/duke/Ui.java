package duke;

public class Ui {
    public Ui() {}
    
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
        buildChatFence();
        print("Bye? I hope it's not forever! Come back soon!");
        buildChatFence();
    }

    public void printAddConfirmation(String message, int size) {
        print("Got it. I've added this task:");
        print(message);
        print(String.format("Now you have %d %s in the list", size, size > 1 ? "tasks" : "task"));
    }
}
