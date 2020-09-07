package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidCommand;
import duke.tasks.Task;

public class TagCommand extends Command {
    private int tagTaskIndex;
    private String tagWord;

    public TagCommand(int taskIndex, String tagWord) {
        this.tagTaskIndex = taskIndex;
        this.tagWord = tagWord;
    }

    @Override
    public String execute(Ui ui, Storage listStorage, TaskList taskList) throws InvalidCommand {
        Task taskToBeTagged = taskList.get(this.tagTaskIndex);
        listStorage.editTaggedTask(taskToBeTagged, this.tagTaskIndex, taskList, this.tagWord);
        return ui.taggedTask(taskToBeTagged, this.tagWord);
    }
}
