import java.util.Scanner;

/**
 * Class for reading user input and user interface
 */
public class UI {

    final static String BYE = "aight imma head out\n";
    final static String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
    final static String LINE = "____________________________________________________________\n";
    Scanner scanner = new Scanner(System.in);

    /**
     * Prints a string of text surrounded by a line of underscores above and below
     * @param str text to be printed
     */
    public static void print(String str) {
        System.out.print(LINE + str + LINE);
    }

    /**
     * Reads user input
     * @return input from user
     */
    String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome message and current number of tasks
     * @param tasks TaskList loaded from storage
     */
    void welcome(TaskList tasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        print(WELCOME + tasks.loadMessage);
    }

    /**
     * Prints goodbye message
     */
    void bye() {
        print(BYE);
    }
}
