package duke;

import java.io.IOException;

/**
 * Exits the main loop in Duke class as well as prints goodbye message when "bye" is received as input
 */
public class ExitCommand extends Command {

    /**
     * Changes isExit to true to quit the main loop. Prints goodbye message and saves the list to hard disk
     * @param tasks TaskList containing Tasks
     * @param ui Ui object that handles printing of any necessary output
     * @param storage Storage object that handles saving Tasks to hard disk
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        isExit = true;
        ui.printGoodbye();
        super.execute(tasks, ui, storage);
    }
}
