/**
 * Cartona is a simple CLI to-do list application. When run from the console, it reads input from the user and
 * adds and deletes items (called Tasks). It also saves the running list tasks to a file that it loads when run.
 *
 * @author Jaya Rengam
 */
public class Cartona {
    /**  */
    private TaskList taskList;


    private Parser parser;
    private Ui ui;
    private Storage storage;

    Cartona() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage("./saved/tasklist.txt");
    }

    /**
     * The main method is run when the program is executed from the command line. It initialises the required classes
     * and loads the list of tasks from a text file, whose relative path is fixed. It then reads input from the
     * console; each line is passed into the parser, which returns a Command. The command is then run; if it is an exit
     * command, the program terminates.
     *
     * @param args
     */
    public static void main(String[] args) {
        Cartona cartona = new Cartona();
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        // Loads TaskList from text file
        cartona.taskList = cartona.storage.getListFromStorage();

        while (true) {
            // Reads next line of input from user
            String nextLine = ui.getNextLineInput();

            try {
                // Parse and execute command
                Command nextCommand = cartona.parser.parseCommand(nextLine);
                nextCommand.execute(cartona.taskList, cartona.ui, cartona.storage);

                // If command is exit command, stop program after running
                if (nextCommand.isExitCmd()) {
                    break;
                }

            // If errors are encountered, print the error message to the console.
            } catch (InvalidInputException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (CartonaException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }
}
