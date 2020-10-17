package duke.commands;

import duke.support.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Adds a ToDo task to the task list.
 */
public class AddToDoCommand extends Command {
    private String commandContent;

    /**
     * Creates a {@AddDeadlineCommand} with given command content.
     *
     * @param commandContent A String of user input for command.
     */
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
