package duke.commands;

import duke.Task;
import duke.TaskList;
import duke.Event;
import duke.Ui;
import duke.Storage;

public class AddEventCommand extends Command{
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
