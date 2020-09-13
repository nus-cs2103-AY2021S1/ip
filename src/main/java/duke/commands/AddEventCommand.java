package duke.commands;

import duke.task.Event;
import duke.support.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;


public class AddEventCommand extends Command {
    private String commandContent;

    public AddEventCommand(String commandContent) {
        this.commandContent = commandContent;
    }


    public String run(TaskList taskList, Storage storage) {
        Task newTask;
        int index = commandContent.indexOf('/');
        String taskContent = commandContent.substring(0, index - 1);
        String taskTime = commandContent.substring(index + 4);
        newTask = new Event(taskContent, taskTime);
        taskList.add(newTask);
        return Ui.addTask(newTask, taskList);
    }

}
