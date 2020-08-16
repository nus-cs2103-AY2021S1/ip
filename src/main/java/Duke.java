/**
 * Entry point of The program
 */
public class Duke {
    public static void main(String[] args) {
        /**
         * Instantiates a new chatBot for the user to interact with
         */
        ChatBot chatBot = new ChatBot("Kai");
        chatBot.welcome();
        chatBot.getInput();

    }
}
