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

    private static final List<Task> TASKS = new ArrayList<>();

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
        String[] tokens = input.split(" ");
        String command = tokens[0].toLowerCase();
        switch (command) {
        case "list": // show tasks available
            Duke.displayTasks();
            break;
        case "done": // label a task as done
            // ASSUMPTION: valid input provided
            int index = Integer.parseInt(tokens[1]) - 1;
            Task task = TASKS.get(index);
            task.markDone();
            Duke.displayMessages(
                    "Okay. So you've done:",
                    task.toString());
            break;
        default: // it's a new task
            Duke.addTask(input);
            break;
        }
    }

    private static void addTask(String taskName) {
        TASKS.add(new Task(taskName));
        Duke.displayMessages(String.format("Ok, you want to: %s", taskName));
    }

    private static void displayTasks() {
        if (TASKS.size() == 0) {
            Duke.displayMessages("You didn't tell me to remind you anything.");
        } else {
            String[] messages = new String[TASKS.size() + 1];
            messages[0] = "Right, you said you wanted to:";

            for (int i = 0; i < TASKS.size(); i++) {
                messages[i + 1] = String.format("%3d: %s", i + 1, TASKS.get(i));
            }

            Duke.displayMessages(messages);
        }
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
