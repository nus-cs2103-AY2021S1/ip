/**
 * The PandaBot class runs the main
 */
public class PandaBot {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a new PandaBot object.
     * @param fileName
     */
    public PandaBot(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        tasks = new TaskList(storage.load());
    }
    
    public static void main(String[] args) {
        new PandaBot("PandaBot_Save.txt").run();
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
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }
        
}
