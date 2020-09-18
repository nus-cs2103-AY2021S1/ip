package duke;

import java.io.IOException;

import command.Command;
import command.Result;
import ui.Ui;


/**
 * The Dukebot programme implements an application that assists the user in managing their tasks.
 *
 * @author  Ryan Lim
 */
public class Duke {
    /** variable to check if duke is still running */
    private boolean isRunning = true;
    /** ArrayList to hold all tasks keyed in by the user for the session */
    private final TaskList taskList;
    /** Parser which handles user input */
    private final Parser parser;
    /** Ui to handle the displaying of duke messages to user*/
    private final Ui ui;
    /** Storage which manages the saving of tasks from the session into the hard disk  */
    private final Storage taskStorage;
    private final Storage aliasStorage;

    /**
     * initialises Duke bot
     */
    public Duke() {
        Parser parser;
        TaskList taskList;
        this.taskStorage = new Storage("./data/data.txt");
        this.aliasStorage = new Storage("./data/alias.txt");
        this.ui = new Ui();
        try {
            parser = new Parser(this.aliasStorage.load());
            taskList = new TaskList(this.taskStorage.load());
        } catch (IOException e) {
            parser = new Parser();
            taskList = new TaskList();
        }
        this.parser = parser;
        this.taskList = taskList;
    }

    /**
     * Greets user
     * @return the greeting message
     */
    public String greet() {
        return ui.greet();
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
            this.taskStorage.save(this.taskList);
            this.aliasStorage.save(this.parser);
        } catch (IOException e) {
            System.out.println("Master i am unable to save the file!");
        }
    }

    /**
     * Terminates the duke program.
     */
    private void exit() {
        this.isRunning = false;
    }

    /**
     * Retrieves the intended command to execute.
     * @param userInput The command and the parameters given by the user.
     * @return the command to be executed
     */
    public Command getCommand(String userInput) {
        return this.parser.parse(userInput);
    }

    /**
     * Executes the given command
     * @param command The command to be executed.
     * @return The result from executing the command.
     */
    public Result executeCommand(Command command) {
        return command.execute(this.taskList, this.parser, this.aliasStorage, this.taskStorage, ui);
    }

    /**
     * To begin running the duke program.
     */
    public void run() {
        while (this.isRunning()) {
            String userInput = this.ui.getUserInput();
            Command command = this.getCommand(userInput);
            Result result = executeCommand(command);
            if (command.isEndCommand()) {
                this.exit();
            } else {
                ui.displayResult(result);
            }
        }
    }
}
