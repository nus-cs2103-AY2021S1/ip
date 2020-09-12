package duke.command;

import duke.exception.DukeFileLoadingErrorException;
import duke.exception.DukeEmptyDescriptionException;
import duke.message.Message;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * represents a command to add a to do task
 */
public class ToDoCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, return a command result indicating that the addition of the to do task was successful
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return command result indicating that the addition of the to do task was successful
     * @throws DukeEmptyDescriptionException if the description for the deadline task is empty
     * @throws DukeFileLoadingErrorException if there was an error loading the file
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeEmptyDescriptionException, DukeFileLoadingErrorException {
        Task addedTask = taskList.addToDo(fullCommand);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.addedTaskMessage(addedTask, taskList));
    }
}
