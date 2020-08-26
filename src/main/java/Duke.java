import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String SAVE_FILE_PATH =
            System.getProperty("user.home") + File.separator + ".duke" + File.separator + "tasks.txt";
    private static final String BYE = "bye";

    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            this.tasks.addAllTasks(this.storage.load());
        } catch (IOException e) {
            this.ui.showLoadingError();
        }
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            this.ui.displayGreeting();

            if (this.storage.hasSavedTasks()) {
                this.ui.displayGreetingReminder(this.tasks.tasksCount());
            }

            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase(BYE)) {
                    this.storage.saveTasks(this.tasks.getTasks());
                    break;
                } else {
                    this.handleCommand(input);
                }
                System.out.print("> ");
            }

            this.ui.displayGoodbye();
        } catch (IOException e) {
            this.ui.displayMessages(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Duke(SAVE_FILE_PATH).run();
    }

    private void handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            String command = tokens[0].toLowerCase();
            switch (command) {
            case "list": // show tasks available
                this.ui.displayTasks(this.tasks.getTasks());
                break;
            case "find":
                String term = input.substring("find".length()).strip();
                List<Task> matchingTasks = this.tasks.searchTasks(term);
                this.ui.displayMatchingTasks(matchingTasks);
                break;
            case "done": {
                if (tokens.length < 2) {
                    throw new InvalidInputException("Um, you need to tell me what it is you've done.");
                }
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = this.tasks.getTask(index);
                task.markDone();
                this.ui.displayMessages(
                        "Okay. So you've done:",
                        task.toString());
            }
            break;
            case "delete":
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = this.tasks.getTask(index);
                this.tasks.deleteTask(index);
                this.ui.displayMessages(
                        "Right, you no longer want me to track:",
                        task.toString(),
                        this.ui.getTasksLeftMessage(this.tasks.tasksCount()));
                break;
            case "todo":
            case "deadline":
            case "event": // it's a new task
                this.addTask(command, input);
                break;
            default:
                this.ui.displayMessages("Um, I don't get what you're saying.");
                break;
            }
        } catch (InvalidInputException e) {
            this.ui.displayMessages(e.getMessage());
        }
    }

    private void addTask(String command, String input) throws InvalidTaskException {
        Task task = TaskParser.parseInput(command, input);
        this.tasks.addTask(task);
        this.ui.displayMessages(
                "Okay, you want to:",
                task.toString(),
                this.ui.getTasksLeftMessage(this.tasks.tasksCount()));
    }
}
