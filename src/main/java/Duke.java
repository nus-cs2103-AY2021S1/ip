import java.nio.file.*;
import java.util.List;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        String home = System.getProperty("user.dir");
        Path savePath = Paths.get(home, "data", "duke.txt");
        this.storage = new Storage(savePath);
        this.taskList = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

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
            task = taskList.addTask(new Todo(commands[1]));
            ui.showAdd(task, taskList);
            break;
        case "deadline":
            Parser.checkValidAddCommand(commands);
            task = taskList.addTask(Deadline.create(commands[1]));
            ui.showAdd(task, taskList);
            break;
        case "event":
            Parser.checkValidAddCommand(commands);
            task = taskList.addTask(Event.create(commands[1]));
            ui.showAdd(task, taskList);
            break;
        case "list":
            ui.showTaskList(taskList);
            break;
        case "done":
            task = taskList.markTaskDone(commands);
            ui.showDone(task);
            break;
        case "delete":
            task = taskList.deleteTask(commands);
            ui.showDelete(task, taskList);
            break;
        case "bye":
            ui.showBye();
            storage.save(taskList);
            return false;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return true;
    }


}
