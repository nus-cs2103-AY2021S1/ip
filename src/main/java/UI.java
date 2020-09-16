import java.util.Scanner;

/**
 * Class for reading user input and user interface.
 */
public class UI {

    final static String BYE = "aight imma head out\n";
    final static String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
    final static String LINE = "____________________________________________________________\n";
    Scanner scanner = new Scanner(System.in);

    private static StringBuilder response;

    /**
     * print greeting content
     */

    public static void init() {
        response = new StringBuilder();
    }

    private static void println(String message) {
        response.append(message).append("\n");
    }

    /**
     * Prints a string of text surrounded by a line of underscores above and below.
     *
     * @param str text to be printed.
     */
    public static void print(String str) {
        println(LINE + str + LINE);
    }

//    /**
//     * Reads user input.
//     *
//     * @return input from user.
//     */
//    String getInput() {
//        return scanner.nextLine();
//    }

    /**
     * Prints welcome message and current number of tasks
     *
     * @param tasks TaskList loaded from storage.
     */
    public static void welcome(TaskList tasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        println("Hello from\n" + logo);
        print(WELCOME + tasks.loadMessage);
    }

    /**
     * Prints goodbye message.
     */
    public static void bye() {
        print(BYE);
    }

    public static String getResponse() {
        String responseToReturn = response.toString();
        response.setLength(0);
        return responseToReturn;
    }
}
