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
}
