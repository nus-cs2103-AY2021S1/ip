package duke.commands;

import duke.util.OutputUi;
import duke.util.Storage;
import duke.util.TaskList;
import duke.DukeException;

import java.io.IOException;

/**
 * Exits the main loop in Duke class as well as prints goodbye message when "bye" is received as input.
 */
public class ExitCommand extends Command {

    /**
     * Changes isExit to true to quit the main loop. Prints goodbye message and saves the list to hard disk
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException DukeException.
     * @throws IOException IOException.
     * @return
     */
    public String execute(TaskList tasks, OutputUi ui, Storage storage) throws DukeException {
        isExit = true;

        ui.reset();
        ui.addSentence("byebye pingu miss u");

        super.execute(tasks, ui, storage);

        return ui.getResponse();
    }
}
