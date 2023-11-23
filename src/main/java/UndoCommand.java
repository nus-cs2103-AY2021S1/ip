/**
 * Represents an undo command.
 */
public class UndoCommand extends Command {
    private String afterCommand;

    public UndoCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // undo command should not have an after command
        if (afterCommand != null) {
            return ui.throwDukeException(new DukeException("Please call the undo comamnd properly!"));
        }

        // undo command cannot be executed when nothing has been updated
        int cachedSize = taskList.getCachedTasksSize();
        if (cachedSize == 0) {
            return ui.throwDukeException(new DukeException("No commands to undo!"));
        }


        CachedTask cachedTask = taskList.getLastCachedTask();
        Task taskToUndo = cachedTask.getCachedTask();
        String commandToUndo = cachedTask.getCommand();
        int cachedTaskIndex = cachedTask.getIndexOfCachedTask();
        switch (commandToUndo) {
        case "add":
            taskList.delete(cachedTaskIndex, false);
            return ui.displayDeletedTask(taskToUndo, cachedTaskIndex, true);
        case "delete":
            taskList.recoverTask(cachedTaskIndex, taskToUndo);
            return ui.addTask(taskToUndo, taskList.getTasksSize(), true);
        case "done":
            taskList.unmarkTask(cachedTaskIndex);
            return ui.displayIncompleteTask(taskToUndo);
        default:
            return "This is not possible!";
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
