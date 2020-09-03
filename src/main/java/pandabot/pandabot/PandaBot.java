package pandabot.pandabot;

import pandabot.commands.Command;
import pandabot.exceptions.PandaBotException;
import pandabot.parser.Parser;
import pandabot.storage.Storage;
import pandabot.tasks.TaskList;
import pandabot.ui.Ui;

/**
 * Represents the main driver of the PandaBot program.
 */
public class PandaBot {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private boolean isExit;

    /**
     * Creates a new PandaBot object.
     * @param fileName the file name of the save file
     */
    public PandaBot(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        tasks = new TaskList(storage.load());
        isExit = false;
    }

    public PandaBot() {
        this("PandaBot_GUI_Save.txt");
    }

    /**
     * Runs the PandaBot program from the command line.
     */
    public void run() {
        ui.printWelcome();

        // runs the program
        while (!isExit) {
            try {
                String fullCommand = ui.readCmd();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                ui.printMessage(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (PandaBotException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Returns a String containing the response to the given user input from the GUI.
     *
     * @param input the user input
     * @return a String containing the response to the given user input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (PandaBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a String representation of the welcome message to be displayed onto the GUI
     *
     * @return a String representation of the welcome message
     */
    public String displayWelcome() {
        return ui.displayWelcomeMessage();
    }

    /**
     * Checks whether the program exits.
     *
     * @return true if the program can exit, otherwise false if the program continues to run
     */
    public boolean canExitProgram() {
        return isExit;
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
