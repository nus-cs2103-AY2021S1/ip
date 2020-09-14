package command;

import exception.UndoException;
import storage.Storage;
import taskList.TaskList;
import undoStack.UndoStack;

import java.util.Stack;

/**
 * A subclass of Command.
 * Handles "undo" command.
 */
public class UndoCommand extends Command {
    private static final String UNDO_TITLE = " I've undo the action.";
    private static Stack<TaskList> stack;

    /**
     * A subclass of command.
     */
    public UndoCommand() {
        super();
    }

    /**
     * Executes the command.
     * @param tasks list of existing tasks.
     * @param storage storage of the data.
     * @throws UndoException exception for invalid input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws UndoException {
        TaskList previousTaskList = UndoStack.getPreviousTaskList();
        tasks.setTaskList(previousTaskList);
        storage.save(previousTaskList);
        return UNDO_TITLE;
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
