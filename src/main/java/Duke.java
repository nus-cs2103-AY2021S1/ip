import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean exitProgram = false;
        while (!exitProgram) {
            try {
                ui.showBlankLine();
                String fullCommand = ui.readCommand();
                ui.showLine();
                String[] userInputArgs = fullCommand.split(" ");
                if (fullCommand.equals(Command.BYE.getName())) {
                    exitProgram = true;
                    ui.showGoodbye();
                } else if (userInputArgs[0].equals(Command.LIST.getName())) {
                    try {
                        LocalDate date = LocalDate.parse(userInputArgs[1]);
                        ui.showTasksOnDate(this.tasks.getListOfTasks(date), date);
                    } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                        ui.showAllTasks(this.tasks.getListOfTasks());
                    }
                } else {
                    if (userInputArgs[0].equals(Command.DONE.getName())) {
                        ui.showTaskDone(markTaskDone(userInputArgs));
                    } else if (userInputArgs[0].equals(Command.DELETE.getName())) {
                        Task task = deleteTask(userInputArgs);
                        ui.showTaskDeleted(task, this.tasks.size());
                    } else {
                        Task task = createTask(userInputArgs);
                        this.tasks.addTask(task);
                        ui.showTaskAdded(task, this.tasks.size());
                    }
                    storage.saveTaskList(this.tasks);
                }
            } catch (DukeException err) {
                ui.showError(err.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private Task markTaskDone(String[] userInputArgs) throws DukeException {
        try {
            int idx = Integer.parseInt(userInputArgs[1]);
            return this.tasks.markTaskDone(idx - 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be marked as done has to be provided.");
        }
    }

    private Task deleteTask(String[] userInputArgs) throws DukeException {
        try {
            int idx = Integer.parseInt(userInputArgs[1]);
            return this.tasks.deleteTask(idx - 1);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be deleted has to be provided.");
        }
    }

    private Task createTask(String[] userInputArgs) throws DukeException {
        if (userInputArgs[0].equals(Command.TODO.getName())) {
            String description = Duke.reassembleString(userInputArgs, 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        } else if (userInputArgs[0].equals(Command.DEADLINE.getName())) {
            int byIdx = Arrays.asList(userInputArgs).indexOf("/by");
            if (byIdx < 0) {
                throw new DukeException("The deadline date has to be provided to the deadline task.");
            }
            String description = Duke.reassembleString(userInputArgs, 1, byIdx);
            String by = Duke.reassembleString(userInputArgs, byIdx + 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (by.equals("")) {
                throw new DukeException("The date of a deadline cannot be empty.");
            } else {
                return new Deadline(description, by);
            }
        } else if (userInputArgs[0].equals(Command.EVENT.getName())) {
            int atIdx = Arrays.asList(userInputArgs).indexOf("/at");
            if (atIdx < 0) {
                throw new DukeException("The event date has to be provided to the event task.");
            }
            String description = Duke.reassembleString(userInputArgs, 1, atIdx);
            String at = Duke.reassembleString(userInputArgs, atIdx + 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (at.equals("")) {
                throw new DukeException("The date of an event cannot be empty.");
            } else {
                return new Event(description, at);
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means...");
        }
    }

    private static String reassembleString(String[] arr, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(arr, from, to));
    }
}
