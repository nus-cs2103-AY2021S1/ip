package duke.commands;

import duke.Duke;
import duke.exceptions.CommandException;
import duke.exceptions.DukeException;
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
    public String run(TaskList taskList, Storage storage) throws DukeException {
        try {
            Task newTask;
            newTask = new ToDo(commandContent);
            taskList.add(newTask);
            return Ui.addTask(newTask, taskList);
        } catch (Exception e) {
            throw new CommandException("Sorry! Failed to add a todo. Please check the input format.");

        }

    }


}
