import main.java.manager.Parser;

/**
 * Represents the Duke chat bot.
 */
public class Duke {

    private void printGreeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Runs the Duke bot by printing the greeting message,
     * instantiating a parser to handle user input
     * and printing the goodbye message upon exit.
     */
    public void run() {
        printGreeting();
        new Parser().handleUserInput();
        printGoodbye();
    }

    /**
     * Main method that drives the running of the Duke bot.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
