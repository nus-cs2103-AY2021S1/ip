import java.io.FileNotFoundException;

import util.*;

/**
 * Duke's main class.
 *
 * Consists of a Storage, TaskList and Ui.
 * Contains the program loop in run() method.
 */
public class Duke{
    // Duke's program variables
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke's Default Constructor.
     * Initializes ui, storage and tasks.
     * Loads in save file (if any).
     * Uses a default filePath
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.loadFileContents());
            ui.setGreetings("... Oh! You're back!\nEr, gimme a sec...");
        } catch (FileNotFoundException e) {
            ui.setGreetings("... Who? Never mind. Er-hmm.\n");
            tasks = new TaskList();
        }
    }

    /**
     * Duke's Constructor.
     * Initializes ui, storage and tasks.
     * Loads in save file (if any).
     *
     * @param filePath  File path for save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
            System.out.println("... Oh! You're back!\nEr, gimme a sec...");
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke within a program loop.
     *
     * 1. Displays Greetings
     * 2. Takes in user input.
     * 3. Parse user input.
     * 4. Respond to command in user input.
     * 5. Return to 2 and loop
     * OR save and exit from program.
     */
    public void run() {
        // Greet first
        ui.displayGreetings();

        boolean isExit = false;
        // Loop for program
        while (!isExit) {
            try {
                // Read user input
                String userInput = ui.readUserInput();
                // Parse out the command from the user input
                Command cmd = Parser.parse(userInput);
                // Use switch to process the commands
                switch (cmd) {
                case EXIT:
                    isExit = true;
                    break;
                case LIST:
                    ui.printList(tasks);
                    break;
                case TODO:
                    ui.printMessage(tasks.createTodo(userInput));
                    break;
                case DEADLINE:
                    ui.printMessage(tasks.createDeadline(userInput));
                    break;
                case EVENT:
                    ui.printMessage(tasks.createEvent(userInput));
                    break;
                case INVALID:
                    // Duke does not recognize the commands
                    ui.printOutputSymbol();
                    throw new DukeException("Sorry, I don't recognize what you just entered...");
                case DONE:
                    ui.printMessage(tasks.markTaskDone(userInput));
                    break;
                case DELETE:
                    ui.printMessage(tasks.deleteTask(userInput));
                    break;
                case FIND:
                    ui.printMessage(tasks.searchForKeyword(userInput));
                    break;
                default:
                    throw new DukeException("Sorry I think I something went wrong...");
                }
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                // Always end off with a line breaker
                ui.printLineBreaker();
            }
        }

        // Display farewells
        ui.displayFarewells();

        // Try to Save the data
        try {
            storage.saveToFile(tasks.getTasks());
        } catch (DukeException e) {
            ui.printError(e);
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


    /**
     * Own function to generate a response to user input.
     * Code based off run().
     *
     * @param input User input.
     */
    public String getResponse(String input) {
        // Read user input
        String userInput = input;
        String output = "";
        try {
            // Parse out the command from the user input
            Command cmd = Parser.parse(userInput);
            // Use switch to process the commands
            switch (cmd) {
            case EXIT:
                String error = "\n";
                // Try to Save the data
                try {
                    storage.saveToFile(tasks.getTasks());
                } catch (DukeException e) {
                    error = ui.getError(e);
                } finally {
                    // Return farewells
                   return ui.getFarewells() + error;
                }
            case LIST:
                output = ui.getLineBreaker() + ui.getList(tasks);
                break;
            case TODO:
                output = ui.getOutputSymbol() + tasks.createTodo(userInput);
                break;
            case DEADLINE:
                output = ui.getOutputSymbol() + tasks.createDeadline(userInput);
                break;
            case EVENT:
                output = ui.getOutputSymbol() + tasks.createEvent(userInput);
                break;
            case INVALID:
                // Duke does not recognize the commands
                throw new DukeException("Sorry, I don't recognize what you just entered...");
            case DONE:
                output = ui.getOutputSymbol() + tasks.markTaskDone(userInput);
                break;
            case DELETE:
                output = ui.getOutputSymbol() + tasks.deleteTask(userInput);
                break;
            case FIND:
                output = ui.getOutputSymbol() + tasks.searchForKeyword(userInput);
                break;
            default:
                throw new DukeException("Sorry I think I something went wrong...");
            }
        } catch (DukeException e) {
            output = ui.getLineBreaker() + ui.getError(e);
        } finally {
            return output + ui.getLineBreaker();
        }
    }

    /**
     * Print's Duke's greetings in GUI.
     */
    public String getGreetings() {
        return ui.getGreetings();
    }
}
