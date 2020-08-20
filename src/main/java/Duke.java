import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String IDENT = "\t";
    private static final String LINE = IDENT + "------------------------------";
    private static final String OUTPUT_FORMAT = LINE + "\n%s\n" + LINE;
    private static final String INITIAL_RESPONSE = "Hello! I'm Duke\n\tWhat can I do for you?";
    private static final String EXIT_INPUT = "bye";
    private static final String EXIT_RESPONSE = "Bye. Hope to see you again soon!";


    private static final String LIST_COMMAND = "list";
    private static final List<String> LIST = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String response;
        printResponse(INITIAL_RESPONSE);
        while (true) {
            response = scanner.nextLine();
            if (response.equals(EXIT_INPUT)) {
                printResponse(EXIT_RESPONSE);
                break;
            } else if (response.equals(LIST_COMMAND)) {
                printList();
            } else {
                addToList(response);
                printResponse("added: " + response);
            }
        }
    }

    private static void printResponse(String response) {
        System.out.println(LINE);
        System.out.printf("%s%s\n", IDENT, response);
        System.out.println(LINE);
    }

    private static void addToList(String response) {
        LIST.add(response);
    }

    private static void printList() {
        System.out.println(LINE);
        int num = 0;
        for (String output : LIST) {
            num++;
            System.out.printf("%s%d. %s\n", IDENT, num, output);
        }
        System.out.println(LINE);
    }
}
