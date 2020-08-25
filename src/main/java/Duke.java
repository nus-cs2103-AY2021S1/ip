import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private boolean isRunning = true;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    Storage storage;
    enum commands {
        BYE,
        DONE,
        DELETE,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        DATE
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.printLoadingError();
            this.taskList = new TaskList();
        }
        this.ui.printGreeting();
    }

    public void doneHandler(String[] parameters) throws DukeExceptions.NoUndoneTaskException {
        if (!this.taskList.isEmpty() || this.taskList.allDone()) {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            this.taskList.completeTask(index);
            this.ui.printDoneTask(this.taskList.getTask(index));
        } else {
            throw new DukeExceptions.NoUndoneTaskException();
        }
    }

    public void addTaskHandler(Command command) throws DukeExceptions.IncompleteCommandException { ;
        if (!command.isEmpty()) {
            Task newTask = this.taskList.addTask(command);
            this.ui.printAddedNewTask(newTask, this.taskList.getNoTask());
        } else {
            throw new DukeExceptions.IncompleteCommandException(command.getClass().toString());
        }
    }

    public void deleteTaskHandler(String[] parameters) throws  DukeExceptions.NoTaskToDeleteException {
        if (!this.taskList.isEmpty()) {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task task = this.taskList.deleteTask(index);
            this.ui.printDeleteTask(task, this.taskList.getNoTask());
        } else {
            throw new DukeExceptions.NoTaskToDeleteException();
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void updateFile() {
        try {
            this.storage.save(this.taskList);
        } catch (IOException e) {
            this.ui.printErrorInSaving();
        }
    }

    public void run(String userInput) {
        Command command = this.parser.parse(userInput);
        if (command.getClass() == ByeCommand.class) {
            this.isRunning = false;
            this.ui.printFarewell();
        } else if (command.getClass() == ListCommand.class) {
            this.ui.printTaskList(this.taskList);
        } else if (command.getClass() == DoneCommand.class) {
            try {
                this.doneHandler(command.getParameters());
                this.updateFile();
            } catch (DukeExceptions.NoUndoneTaskException e) {
                DukeExceptions.printNoUndoneTaskError();
            } catch (IndexOutOfBoundsException e) {
                DukeExceptions.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                DukeExceptions.noIndexKeyedError();
            }
        } else if (command.getClass() == TodoCommand.class
                || command.getClass() == EventCommand.class
                || command.getClass() == DeadLineCommand.class ) {
            try {
                this.addTaskHandler(command);
                this.updateFile();
            } catch (DukeExceptions.IncompleteCommandException e) {
                DukeExceptions.printIncompleteCommandError(command.toString().toLowerCase());
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeExceptions.printNoDateInput(command.toString().toLowerCase());
            } catch (DateTimeParseException e) {
                DukeExceptions.printIncorrectDateFormatError();
            }
        } else if (command.getClass() == DelCommand.class ) {
            try {
                this.deleteTaskHandler(command.getParameters());
                this.updateFile();
            } catch (DukeExceptions.NoTaskToDeleteException e) {
                DukeExceptions.printNoTaskToDeleteError();
            } catch (IndexOutOfBoundsException e) {
                DukeExceptions.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                DukeExceptions.noIndexKeyedError();
            }
        } else if (command.getClass() == DateCommand.class ) {
            ui.printGetTaskOnDThisDate(command.getParameters()[0], this.taskList);
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke("./data/data.txt");
        Scanner sc = new Scanner(System.in); //scans for input
        String userInput;

        while (duke.isRunning()) {
            userInput = sc.nextLine();
            try {
                duke.run(userInput);
            } catch (IllegalArgumentException e) {
                DukeExceptions.printUnrecognizableCommandError();
            }
        }
    }
}
