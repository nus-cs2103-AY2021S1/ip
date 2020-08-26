import java.io.File;
import java.io.IOException;
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

    /**
     * Executes an instance of the Duke chatbot. While the user does not input a goodbye command, Duke interprets
     * different commands passed to it and performs different actions, including but not limited to the following:
     * - store a task to be done
     * - mark a task as done
     * - list the tasks to be done
     * - find tasks that match a search term
     * - remove a task from the list of tasks
     */
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

    /**
     * Initializes an instance of Duke, and runs it.
     * @param args The command line args passed to the program
     */
    public static void main(String[] args) {
        new Duke(SAVE_FILE_PATH).run();
    }

    /**
     * Interprets a command that was passed to Duke, and performs a corresponding action based on it. If the command or
     * its arguments were malformed, display an error to the user.
     *
     * @param input The String containing the command, as well as its arguments
     */
    private void handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            String command = tokens[0].toLowerCase();
            switch (command) {
            case "list": // show tasks available
                this.ui.displayTasks(this.tasks.getTasks());
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
