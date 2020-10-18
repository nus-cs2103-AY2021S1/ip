package duke.commands;

import duke.Duke;
import duke.exceptions.CommandException;
import duke.exceptions.DukeException;
import duke.support.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a Event task to the task list.
 */
public class AddEventCommand extends Command {
    private String commandContent;

    /**
     * Creates a {@code AddDeadlineCommand} with given command content.
     *
     * @param commandContent A String of user input for command.
     */
    public AddEventCommand(String commandContent) {
        this.commandContent = commandContent;
    }

    /**
     * Parses user's input into task index, time and content and creates a {@code Event} task for the task list.
     */
    public String run(TaskList taskList, Storage storage) throws DukeException {
        try {
            Task newTask;
            int index = commandContent.indexOf('/');
            String taskContent = commandContent.substring(0, index - 1);
            String taskTime = commandContent.substring(index + 4);
            newTask = new Event(taskContent, taskTime);
            taskList.add(newTask);
            return Ui.addTask(newTask, taskList);
        } catch (Exception e) {
            throw new CommandException("Sorry! Failed to add a event. Please check the input format.");
        }

    }

}
