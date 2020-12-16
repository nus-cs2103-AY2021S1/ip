import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents Ui class and consists of methods to handle things that related to User Interaction.
 */
public class Ui {

    private static final String horizontal_line = "------------------------------------------";
    private static final String logo = "        \n"

            + "____       _      _        _ \n"
            + "| __ )    (_)    | |      | | \n"
            + "|  _ \\      | |    | |      | | \n"
            + "| |_) |    | |    | |      | |  \n"
            + "|____/   |_|   |｜  ｜| \n";
    private static String filepath;
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    protected Ui(String filePath) {
        scanner = new Scanner(System.in);
        this.filepath = filePath;
    }

    /**
     * Returns the user input.
     * @return String of user input.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println(horizontal_line);
    }
    public String printLine() {
        return horizontal_line;
    }


    /**
     * Prints the welcome message
     */
    public static String welcome_message() {
        String message = "Hello from Bill \n" + logo + "\n";
        message = message + horizontal_line + "\n ";
        message = message + "Hello! I'm Bill \n" + "What Can I do for you? \n";
        message = message + "\n ( You can find a list of command by typing \"help \" comand \n eg: help ) \n";
        message = message + horizontal_line + "\n ";
        Storage storage = new Storage(filepath);
        try {
            return message + storage.checkFile(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
    public static String tr() {
        return "hello";
    }

    /**
     * Prints the exit message.
     */
    public static void session_end() {
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
    public static String exitMessage() {
        return horizontal_line + "\n" + "Bye. Hope to see you again soon! \n" + horizontal_line + "\n";
    }

    /**
     * Returns the error message.
     * @param message Error message.
     */
    public static String getErrorMessage(String message) {
        return "OOPS!!! " + message;
    }
}
