import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String BYE = "bye";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Ui.displayGreeting();

            if (Storage.hasSavedTasks()) {
                TaskList.addAllTasks(Storage.load());
                Ui.displayGreetingReminder(TaskList.tasksCount());
            }

            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(BYE)) {
                    Storage.saveTasks(TaskList.getTasks());
                    break;
                } else {
                    Duke.handleCommand(input);
                }
                System.out.print("> ");
            }

            Ui.displayGoodbye();
        } catch (IOException e) {
            Ui.displayMessages(e.getMessage());
        }
    }

    private static void handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            String command = tokens[0].toLowerCase();
            switch (command) {
            case "list": // show tasks available
                Ui.displayTasks(TaskList.getTasks());
                break;
            case "done": {
                if (tokens.length < 2) {
                    throw new InvalidInputException("Um, you need to tell me what it is you've done.");
                }
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = TaskList.getTask(index);
                task.markDone();
                Ui.displayMessages(
                        "Okay. So you've done:",
                        task.toString());
            }
            break;
            case "delete":
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = TaskList.getTask(index);
                TaskList.deleteTask(index);
                Ui.displayMessages(
                        "Right, you no longer want me to track:",
                        task.toString(),
                        Ui.getTasksLeftMessage(TaskList.tasksCount()));
                break;
            case "todo":
            case "deadline":
            case "event": // it's a new task
                Duke.addTask(command, input);
                break;
            default:
                Ui.displayMessages("Um, I don't get what you're saying.");
                break;
            }
        } catch (InvalidInputException e) {
            Ui.displayMessages(e.getMessage());
        }
    }

    private static void addTask(String command, String input) throws InvalidTaskException {
        Task task = TaskParser.parseInput(command, input);
        TaskList.addTask(task);
        Ui.displayMessages(
                "Okay, you want to:",
                task.toString(),
                Ui.getTasksLeftMessage(TaskList.tasksCount()));
    }
}
