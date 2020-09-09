import java.nio.file.*;

/**
 * Duke is a Personal Assistant bot that helps you to keep track of various tasks.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        String home = System.getProperty("user.dir");
        Path savePath = Paths.get(home, "data", "duke.txt");
        this.storage = new Storage(savePath);
        this.tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Starts duke
     */
    public void run() {
        ui.showWelcome();
        boolean toContinue = true;
        while (toContinue) {
            try {
                String command = ui.readCommand();
                String[] commandBlocks = Parser.parse(command);
                ui.showLine();
                toContinue = handleCommand(commandBlocks);
            } catch (DukeException dukeException) {
                ui.showException(dukeException);
            } finally {
                ui.showLine();
            }
        }
    }

    private boolean handleCommand(String[] commands) throws DukeException {
        Task task;
        switch (commands[0]) {
        case "todo":
            Parser.checkValidAddCommand(commands);
            task = tasks.addTask(new Todo(commands[1]));
            ui.showAdd(task, tasks);
            break;
        case "deadline":
            Parser.checkValidAddCommand(commands);
            task = tasks.addTask(Deadline.create(commands[1]));
            ui.showAdd(task, tasks);
            break;
        case "event":
            Parser.checkValidAddCommand(commands);
            task = tasks.addTask(Event.create(commands[1]));
            ui.showAdd(task, tasks);
            break;
        case "list":
            ui.showTaskList(tasks);
            break;
        case "done":
            task = tasks.markTaskDone(commands);
            ui.showDone(task);
            break;
        case "delete":
            task = tasks.deleteTask(commands);
            ui.showDelete(task, tasks);
            break;
        case "bye":
            ui.showBye();
            storage.save(tasks);
            return false;
        case "find":
            String result = tasks.getSearchResult(commands);
            ui.showSearchResults(result);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return true;
    }


}
