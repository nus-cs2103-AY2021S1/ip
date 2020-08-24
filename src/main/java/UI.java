import java.util.Scanner;

public class UI {
    private static final String DIVIDER = "-----------------------------------------------------";
    private static final String HELLO_MESSAGE = "Greetings! I am Duke.\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye! See you around!";
    private static final String ERROR_MESSAGE = "Hmm I didn't quite catch that.";

    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showHelloMessage() {
        showLine();
        System.out.println(HELLO_MESSAGE);
        showLine();
    }

    public void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public void showErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE + "\n" + message);
    }

    public String readCommand() throws UIException {
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.equals("")) {
                return line;
            } else {
                throw new UIException("Input cannot be empty.");
            }
        } else {
            throw new UIException("Invalid call to readCommand");
        }
    }

    public void printResult(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
