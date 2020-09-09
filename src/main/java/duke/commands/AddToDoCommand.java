package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

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
