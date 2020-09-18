package command;

import exception.DeleteException;
import storage.Storage;
import task.Task;
import taskList.TaskList;
import undoStack.UndoStack;

import java.util.ArrayList;

/**
 * A subclass of Command.
 * Handles "delete" command.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_TITLE = "Noted. I've removed this task:";
    private String[] input;

    /**
     * A subclass of command.
     * @param input user input.
     */
    public DeleteCommand(String[] input) {
        super();
        this.input = input;
    }

    /**
     * Execute the command.
     * @param tasks list of existing tasks.
     * @param storage storage of data.
     * @throws DeleteException exception for invalid input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DeleteException {
        UndoStack.add(tasks);
        ArrayList<Task> store = tasks.getTaskList();
        if (this.input.length == 1) { //incomplete done command
            throw new DeleteException("☹ OOPS!!! The description of a delete cannot be empty.");
        }

        int indexOfMarkingTask = Integer.parseInt(this.input[1]) - 1;
        if (indexOfMarkingTask + 1 > store.size() || indexOfMarkingTask + 1 <= 0) {
            throw new DeleteException("☹ OOPS!!! There is no such task.");
        }

        //complete done command
        Task deletingTask = store.get(indexOfMarkingTask);
        store.remove(indexOfMarkingTask);
        storage.save(new TaskList(store));
        return DELETE_TITLE + "\n"
                + deletingTask + "\n"
                + "Now you have " + store.size() + " tasks in the list.";
    }

    /**
     * Returns isDone to stop user from entering command.
     * @return false to continue to accept user input.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

