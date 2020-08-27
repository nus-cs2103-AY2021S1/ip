package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private final String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String numberCharacter = this.fullCommand.substring(5);
            int index = Integer.parseInt(numberCharacter) - 1;

            Task taskToChange = taskList.getItem(index);
            taskToChange.markDone();

            storage.modifyTask(taskToChange, index);

            ui.showDone();
            ui.print(taskToChange.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException a) {
            throw new DukeException("Please enter a valid task number for me to mark as done");
        }
    }
}
