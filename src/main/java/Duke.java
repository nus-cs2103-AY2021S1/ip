import java.io.FileNotFoundException;

/**
 * Duke's main class.
 *
 * Consists of a Storage, TaskList and Ui.
 * Contains the program loop in run() method.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
                switch(cmd) {
                    case EXIT:
                        isExit = true;
                        break;
                    case LIST:
                        ui.printList(tasks);
                        break;
                    case TODO:
                        ui.printOutputSymbol();
                        tasks.createTodo(userInput);
                        break;
                    case DEADLINE:
                        ui.printOutputSymbol();
                        tasks.createDeadline(userInput);
                        break;
                    case EVENT:
                        ui.printOutputSymbol();
                        tasks.createEvent(userInput);
                        break;
                    case INVALID:
                        // Duke does not recognize the commands
                        ui.printOutputSymbol();
                        throw new DukeException("Sorry, I don't recognize what you just entered...");
                    case DONE:
                        ui.printOutputSymbol();
                        tasks.markTaskDone(userInput);
                        break;
                    case DELETE:
                        ui.printOutputSymbol();
                        tasks.deleteTask(userInput);
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
            storage.saveToFile(tasks.getList());
        } catch (DukeException e) {
            ui.printError(e);
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
