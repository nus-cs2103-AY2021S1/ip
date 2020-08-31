package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isBye = true;
    }


    /**
     * Shows closing message to user before Dino closes.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where task actions are done.
     * @param ui Ui that shows closing message.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.bye();
    }

    /**
     * Returns closing string to user before Dino closes.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where task actions are done.
     * @param ui Ui that shows closing message.
     * @return String closing message
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        return "Rawr. Hope to see you again soon! ><";
    }
}
