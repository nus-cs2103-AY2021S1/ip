package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class TagCommand extends Command {
    private int taskIndex;
    private String taskTag;

    public TagCommand(int taskIndex, String taskTag) {
        this.taskIndex = taskIndex;
        this.taskTag = taskTag;
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        Task task = taskList.get(taskIndex - 1);
        String oldTag = task.setTag(taskTag);
        return Ui.setTaskTag(taskIndex, oldTag, taskTag);
    }
}
