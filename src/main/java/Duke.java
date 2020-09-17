import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import manager.Parser;

/**
 * Represents the Duke chat bot.
 */
public class Duke {

    private final Parser parser = new Parser();

    private void printGreeting() {
        System.out.println("Hey there, I'm Mr. Meeseeks, look at me!\n");
        System.out.println("Type 'help' to display all available commands!\n");
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Runs the Duke bot by printing the greeting message,
     * instantiating a parser to handle user input from a scanner
     * and printing the goodbye message upon exit.
     */
    public void run() {
        printGreeting();
        this.parser.handleUserInput();
        printGoodbye();
    }

    /**
     * Obtains the response from the bot given the user input.
     * @param input as a provided string
     * @return output response
     */
    public String getResponse(String input) {
        return this.parser.handleUserInput(input);
    }

    /**
     * Starts the Duke bot by printing the greeting message,
     * instantiating a parser to parse saved tasks
     * @return greeting message and saved tasks as a string
     */
    public String start() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = initializePrintStream(output);

        printGreeting();
        this.parser.parseSavedTasks();

        setOutput(stream);
        return output.toString();
    }

    private PrintStream initializePrintStream(ByteArrayOutputStream outputStream) {
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream output = System.out;
        System.setOut(printStream);
        return output;
    }

    private void setOutput(PrintStream stream) {
        System.out.flush();
        System.setOut(stream);
    }

    /**
     * Main method that drives the running of the Duke bot.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
