package duke.commands;

import duke.support.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class AddToDoCommand extends Command {
    private String commandContent;

    public AddToDoCommand(String commandContent) {
        this.commandContent = commandContent;
    }


    @Override
    public String run(TaskList taskList, Storage storage) {
        Task newTask;
        newTask = new ToDo(commandContent);
        taskList.add(newTask);
        return Ui.addTask(newTask, taskList);
    }


}
