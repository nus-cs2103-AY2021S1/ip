import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String GREET_MESSAGE = "Hello! I'm AiBot :)\n\tEnter the command you would like to do\n\tEnter 'bye' to exit";
    private static final String EXIT_MESSAGE = "Bye. See you again soon!";
    private static final String DIVIDER = "\n\t-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";

    private static final List<String> actionList = new ArrayList<>();

    private static void printResponse(String message) {
        System.out.println(DIVIDER.concat("\t".concat(message.concat("\n"))).concat(DIVIDER));
    }

    private static void addAction(String action) {
        actionList.add(action);
        printResponse("added: ".concat(action));
    }

    private static void listActions() {
        if (actionList.size() == 0) {
            printResponse("No action added yet...");
            return;
        }
        StringBuilder actions = new StringBuilder();
        for (int i = 0; i < actionList.size(); i++) {
            String action = String.format("%d. %s", (i + 1), actionList.get(i));
            if (i > 0) {
                action = "\n\t".concat(action);
            }
            actions.append(action);
        }
        printResponse(actions.toString());
    }

    private static void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            if (command.equalsIgnoreCase("list")) {
                listActions();
            } else {
                addAction(command);
            }
            command = scanner.nextLine();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        printResponse(GREET_MESSAGE);
        listenCommand();
        printResponse(EXIT_MESSAGE);
    }
}
