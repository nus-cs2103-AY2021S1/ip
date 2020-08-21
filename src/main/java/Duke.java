import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final Path filePath = Paths.get(".", "data", "duke.txt");

    private TaskList tasks;
    private final Database db;
    private final Ui ui;

    Duke() {
        this.ui = new Ui();
        this.db = new Database(Duke.filePath);
        try {
            this.tasks = new TaskList(this.db.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void parseInputs(String userInput) throws DukeException {
        String[] commandInputs = userInput.split(" ", 2);

        if (commandInputs.length == 0) {
            throw new DukeException("Something went wrong when parsing your inputs!");
        }

        Command command;
        try {
            command = Command.valueOf(commandInputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Command.UNKNOWN;
        }

        String commandDetails;

        switch (command) {
            case LIST:
                this.ui.print(String.format("Here are the tasks in your list:\n%s", this.tasks));
                break;

            case DONE:
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to mark a task as done, but no task was specified!");
                }

                commandDetails = commandInputs[1];

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    Task task = this.tasks.getTask(taskId);
                    task.markAsDone();

                    this.db.updateExistingTask(taskId, task);

                    this.ui.print(String.format("Nice! I've marked this task as done:\n%s", task));
                } catch (NumberFormatException e) {
                    throw new DukeException(
                            "Please key in only the integer representing the task you want to mark as complete!");
                }

                break;

            case DELETE:
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to delete a task, but no task was specified!");
                }

                commandDetails = commandInputs[1];

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    Task task = this.tasks.deleteTask(taskId);

                    this.db.deleteExistingTask(taskId);

                    this.ui.print(
                            String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                                    task, tasks.size()));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please key in only the integer representing the task you want to delete!");
                }

                break;

            case TODO:
            case DEADLINE:
            case EVENT:
                Task task;

                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to create new task without providing details!");
                }

                commandDetails = commandInputs[1];

                if (command == Command.TODO) {
                    task = new Todo(commandDetails);
                } else if (command == Command.DEADLINE) {
                    String[] deadlineDetails = commandDetails.split("/by", 2);

                    if (deadlineDetails.length < 2) {
                        throw new DukeException("Attempted to create task with deadline without specifying deadline!");
                    }

                    String description = deadlineDetails[0].trim();
                    String by = deadlineDetails[1].trim();

                    task = new Deadline(description, by);
                } else {
                    // Last case would be creating an event
                    String[] eventDetails = commandDetails.split("/at", 2);

                    if (eventDetails.length < 2) {
                        throw new DukeException("Attempted to create event without specifying time!");
                    }

                    String description = eventDetails[0].trim();
                    String at = eventDetails[1].trim();

                    task = new Event(description, at);
                }

                this.tasks.addTask(task);

                this.db.saveNewTask(task);

                this.ui.print(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                        task, this.tasks.size()));
                break;

            case UNKNOWN:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public void run() {
        this.ui.print("Hello! I'm Duke\nWhat can I do for you?");

        String userInput;
        while (!(userInput = this.ui.readCommand()).equals("bye")) {
            try {
                this.parseInputs(userInput);
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            }
        }

        this.ui.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
