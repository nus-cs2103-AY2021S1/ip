package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    private String commandContent;

    public AddCommand(String commandContent) {
        this.commandContent = commandContent;
    }

    public String run(TaskList taskList, Storage storage) {
        Task newTask;
        newTask = new Task(commandContent);
        taskList.add(newTask);

        return Ui.addTask(newTask, taskList);
    }
}
