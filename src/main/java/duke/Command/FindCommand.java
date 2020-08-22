package duke.Command;

import duke.Exception.DukeException;

import duke.Storage;

import duke.Task.TaskList;

import duke.Ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList resultWithKeyword = taskList.filter(keyword);
        ui.showFindMessage(resultWithKeyword);
    }
}
