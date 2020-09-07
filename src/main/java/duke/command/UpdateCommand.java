package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command {
    private final int index;
    private final String updateTime;

    /**
     * Represents a UpdateCommand
     * @param content the content of this UpdateCommand
     * @throws DukeException if the task does not exist in the list or the input format is wrong
     */
    public UpdateCommand(String content) throws DukeException {
        try {
            String[] components = content.split(" ", 2);
            this.index = Integer.parseInt(components[0]) - 1;
            this.updateTime = components[1];

        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, I can't seem to find the task...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please take note of the format(white space) !");
        }
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = list.getList().get(this.index);
            task.update(this.updateTime);
            storage.generateTxt(list);
            return ui.showUpdate(task);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I can't seem to find the task...");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
