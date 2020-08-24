import java.util.Scanner;

/**
 * Encapsulates the ui into a class. The class supports showing the welcome message,
 * reading commands from the user, printing a line and showing error messages.
 */
public class Ui {

    /**
     * Show the loading error message.
     */
    public void showLoadingError() {
        System.out.println("File not found. Created a file.");
    }

    /**
     * Show the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "____________________________________________________________\n" +
                "  Hello! I'm Duke\n" +
                "  What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Read a command from the user.
     * @return The command string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        return string1;
    }

    /**
     * Print the divider line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Show the error message.
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
