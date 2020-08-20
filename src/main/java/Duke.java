import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String IDENT = "\t";
    private static final String LINE = IDENT + "------------------------------";
    private static final String INITIAL_RESPONSE = "Hello! I'm Duke\n\tWhat can I do for you?";
    private static final String EXIT_INPUT = "bye";
    private static final String EXIT_RESPONSE = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String response;
        responseOutput(INITIAL_RESPONSE);
        while (true) {
            response = scanner.nextLine();
            int output = responseOutput(response);
            if (output == -1) {
                break;
            }
        }
    }
    private static int responseOutput(String response) {
        int output = 1;
        if (response.equals(EXIT_INPUT)) {
            response = EXIT_RESPONSE;
            output = -1;
        }
        System.out.println(LINE);
        System.out.printf("%s%s%n",IDENT, response);
        System.out.println(LINE);
        return output;
    }
}
