package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.InvalidFindFormatException;

public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage)
            throws InvalidFindFormatException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length == 1) {
            throw new InvalidFindFormatException();
        }

        String keyword = fullCommand.substring(5).trim();

        TaskList matchingTaskList = taskList.findTask(keyword);

        ui.findTaskMessage(matchingTaskList.toString(), matchingTaskList.getTaskListLength());

    }
}
