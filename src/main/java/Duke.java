import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            "██████╗ ███████╗███╗   ██╗███████╗██████╗ ██╗ ██████╗████████╗\n"
                    + "██╔══██╗██╔════╝████╗  ██║██╔════╝██╔══██╗██║██╔════╝╚══██╔══╝\n"
                    + "██████╔╝█████╗  ██╔██╗ ██║█████╗  ██║  ██║██║██║        ██║\n"
                    + "██╔══██╗██╔══╝  ██║╚██╗██║██╔══╝  ██║  ██║██║██║        ██║\n"
                    + "██████╔╝███████╗██║ ╚████║███████╗██████╔╝██║╚██████╗   ██║\n"
                    + "╚═════╝ ╚══════╝╚═╝  ╚═══╝╚══════╝╚═════╝ ╚═╝ ╚═════╝   ╚═╝\n";
    private static final String BYE = "bye";
    private static final String GOODBYE_MESSAGE = "Ok lor like that lor.";

    // ASSUMPTION: There will be no more than 100 tasks.
    private static final List<String> TASKS = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hi, I'm\n" + LOGO);
        Duke.displayMessages("What do you need this time 😫");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(BYE)) {
                    break;
                } else {
                    Duke.handleCommand(input);
                }
                System.out.print("> ");
            }

            Duke.displayMessages(GOODBYE_MESSAGE);
        }
    }

    private static void handleCommand(String input) {
        if ("list".equals(input.toLowerCase())) {
            Duke.displayTasks();
        } else {
            Duke.addTask(input);
        }
    }

    private static void addTask(String task) {
        TASKS.add(task);
        Duke.displayMessages(String.format("Ok, you want to: %s", task));
    }

    private static void displayTasks() {
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println("     Right, you said you wanted to:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.printf("     %3d: %s\n", i + 1, TASKS.get(i));
        }
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();
    }

    private static void displayMessages(String... messages) {
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        for (String message : messages) {
            System.out.printf("     %s\n", message);
        }
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();
    }
}
