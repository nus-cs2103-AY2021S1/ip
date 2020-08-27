/**
 * Represents the main driver of the PandaBot program.
 */
public class PandaBot {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a new PandaBot object.
     * @param fileName the file name of the save file
     */
    public PandaBot(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        tasks = new TaskList(storage.load());
    }
    
    /**
     * Runs the PandaBot program.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;

        // runs the program
        while (!isExit) {
            try {
                String fullCommand = ui.readCmd();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PandaBotException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Executes the PandaBot program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new PandaBot("PandaBot_Save.txt").run();
    }


}
