import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "_________________________________________________________________________________";

    private static final String LOGO = "             _        ______   _____    ______   _____  \n" +
            "     /\\     | |      |  ____| |  __ \\  |  ____| |  __ \\ \n" +
            "    /  \\    | |      | |__    | |__) | | |__    | |  | |\n" +
            "   / /\\ \\   | |      |  __|   |  _  /  |  __|   | |  | |\n" +
            "  / ____ \\  | |____  | |      | | \\ \\  | |____  | |__| |\n" +
            " /_/    \\_\\ |______| |_|      |_|  \\_\\ |______| |_____/ \n";

    private static void printToConsole(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private static String convertTaskListToString(List<String> tasks) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i+1));
            sb.append(". ");
            sb.append(tasks.get(i));
            sb.append('\n');
        }

        // remove last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        System.out.println(LOGO);
        printToConsole("Hi I'm Alfred! How can I help you today?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            switch (input) {
            case "bye":
                printToConsole("Goodbye!");
                return;
            case "list":
                printToConsole(convertTaskListToString(tasks));
                break;
            default:
                tasks.add(input);
                printToConsole("added: " + input);
            }
        }

        sc.close();
    }
}