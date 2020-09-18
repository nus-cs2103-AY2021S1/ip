import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    public Duke() {
        ui = new UI();
        tasks = new TaskList();
        String directory = System.getProperty("user.dir");
        Path filePath = Paths.get(directory, "data", "data.txt");
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.displayError(e);
        }
    }

    public void execute() {
        ui.greetings();
        boolean isHalted = false;
        while (!isHalted) {
            try {
                String command = ui.readCommand();
                Parser parser = new Parser(command);
                String commandType = parser.getCommandType();
                switch (commandType) {
                case "bye": {
                    isHalted = true;
                    break;
                }
                case "list": {
                    ui.displayMessage("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); ++i) {
                        ui.displayFormat("%d. %s\n", i + 1, tasks.get(i));
                    }
                    break;
                }
                case "done": {
                    int index = parser.getIndex();
                    if (index >= tasks.size()) {
                        throw new DukeException(":( OOPS!!! Task index not found.");
                    }
                    tasks.get(index).markAsDone();
                    ui.displayMessage("Nice! I've marked this task as done:");
                    ui.displayMessage(tasks.get(index).toString());
                    break;
                }
                case "delete": {
                    int index = parser.getIndex();
                    if (index >= tasks.size()) {
                        throw new DukeException(":( OOPS!!! Task index not found.");
                    }
                    ui.displayMessage("Noted. I've removed this task:");
                    ui.displayMessage(tasks.get(index).toString());
                    tasks.removeTask(index);
                    ui.displayFormat("Now you have %d tasks in the list.\n", tasks.size());
                    break;
                }
                case "add": {
                    Task newTask = parser.getTask();
                    tasks.addTask(newTask);
                    ui.displayMessage("Got it. I've added this task:");
                    ui.displayMessage(newTask.toString());
                    ui.displayFormat("Now you have %d tasks in the list\n", tasks.size());
                    break;
                }
                case "find": {
                    String keyword = parser.getDescription();
                    int counter = 0;
                    for (Task task: tasks.getTasks()) {
                        if (task.description.contains(keyword)) {
                            if (counter == 0) {
                                ui.displayMessage("Here are the matching tasks in your list:");
                            }
                            ++counter;
                            ui.displayFormat("%d.%s\n", counter, task.toString());
                        }
                    }
                    if (counter == 0) {
                        ui.displayMessage("No task was found.");
                    }
                    break;
                }
                default: {
                    throw new Exception("Unexpected error");
                }
                }
            } catch (Exception e) {
                ui.displayError(e);
            }
        }

        try {
            storage.saveData(tasks.getTasks());
        } catch (Exception e) {
            ui.displayError(e);
        }
        ui.bye();
    }

    public static void main(String[] args) throws Exception {
        new Duke().execute();
    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            return parser.run(tasks, storage, ui);
        } catch (Exception e) {
            return "Unknown command";
        }
    }
}
