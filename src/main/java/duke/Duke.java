package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.IOException;
import command.*;
import task.Task;
/**
 * The Dukebot programme implements an application that assists the user in managing their tasks.
 *
 * @author  Ryan Lim
 */
public class Duke {
    /** variable to check if duke is still running */
    private boolean isRunning = true;
    /** ArrayList to hold all tasks keyed in by the user for the session */
    private TaskList taskList;
    /** UI where the user will interact with the Dukebot */
    private final Ui ui;
    /** Parser which handles user input */
    private final Parser parser;
    /** Storage which manages the saving of tasks from the session into the hard disk  */
    private final Storage storage;


    /**
     * initialises Duke bot
     *
     * @param filePath Path which points to the desired location to store tasks
     */
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

    /**
     * Handles Tasks which are marked done and updates it in both hard disk accordingly as well as the task list.
     *
     * @param parameters the index of the task to be marked done.
     * @throws DukeExceptions.NoUndoneTaskException
     */

    private void doneHandler(String[] parameters) throws DukeExceptions.NoUndoneTaskException {
        if (!this.taskList.isEmpty() || this.taskList.allDone()) {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            this.taskList.completeTask(index);
            this.ui.printDoneTask(this.taskList.getTask(index));
            this.updateFile();
        } else {
            throw new DukeExceptions.NoUndoneTaskException();
        }
    }

    /**
     * Handles addition of new Tasks and updates it in both hard disk accordingly as well as the task list.
     *
     * @param command the command which indicates which type of task to create.
     * @throws DukeExceptions.IncompleteCommandException
     */

    private void addTaskHandler(Command command) throws DukeExceptions.IncompleteCommandException { ;
        if (!command.isEmpty()) {
            Task newTask = this.taskList.addTask(command);
            this.ui.printAddedNewTask(newTask, this.taskList.getNoTask());
            this.updateFile();
        } else {
            throw new DukeExceptions.IncompleteCommandException(command.getClass().toString());
        }
    }

    /**
     * Handles addition of new Tasks and updates it in both hard disk accordingly as well as the task list.
     *
     * @param parameters index of task to delete.
     * @throws DukeExceptions.NoTaskToDeleteException if tasklist is empty
     */

    private void deleteTaskHandler(String[] parameters) throws  DukeExceptions.NoTaskToDeleteException {
        if (!this.taskList.isEmpty()) {
            int index = Integer.parseInt(parameters[0].strip()) - 1;
            Task task = this.taskList.deleteTask(index);
            this.ui.printDeleteTask(task, this.taskList.getNoTask());
            this.updateFile();
        } else {
            throw new DukeExceptions.NoTaskToDeleteException();
        }
    }

    /**
     * Returns True if Duke bot is still running.
     *
     * @return boolean value indicating if Duke bot is still running or not.
     */
    public boolean isRunning() {
        return this.isRunning;
    }


    /**
     * saves tasks to hard disk
     */
    private void updateFile() {
        try {
            this.storage.save(this.taskList);
        } catch (IOException e) {
            this.ui.printErrorInSaving();
        }
    }

    /**
     * Takes in a user input as a string, parses it and gets fed the appropriate commands to be handled.
     *
     * @param userInput commands and parameters that the user inputs through the user interface
     */
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
            } catch (DukeExceptions.IncompleteCommandException e) {
                DukeExceptions.printIncompleteCommandError();
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeExceptions.printNoDateInput();
            } catch (DateTimeParseException e) {
                DukeExceptions.printIncorrectDateFormatError();
            }
        } else if (command.getClass() == DelCommand.class ) {
            try {
                this.deleteTaskHandler(command.getParameters());
            } catch (DukeExceptions.NoTaskToDeleteException e) {
                DukeExceptions.printNoTaskToDeleteError();
            } catch (IndexOutOfBoundsException e) {
                DukeExceptions.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                DukeExceptions.noIndexKeyedError();
            }
        } else if (command.getClass() == DateCommand.class ) {
            ui.printGetTaskOnDThisDate(command.getParameters()[0], this.taskList);
        } else if (command.getClass() == FindCommand.class) {
            ui.printFindKeyword(command.getParameters()[0], this.taskList);
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
