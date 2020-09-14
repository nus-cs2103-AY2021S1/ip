package duke.commands;

import duke.lists.DeletedTaskList;
import duke.lists.CommandList;
import duke.Ui;
import duke.Storage;
import duke.lists.TaskList;

import duke.exceptions.DukeInvalidUndoException;
import duke.tasks.Task;

/**
 * Command to undo the last edit to the task list
 */
public class UndoCommand extends Command {

    /**
     * Performs necessary tasks to carry out the undo command
     * 
     * @param ui ui which generates appropriate response to the user
     * @param storage storage where data is stored
     * @param taskList taskList which stores information about tasks
     * @param commandList commandList which stores information about previous commands
     * @param deletedTaskList deletedTaskList which stores information about deleted tasks
     * @throws DukeInvalidUndoException If there are no commands to undo
     */
    public void executeCommand (Ui ui, Storage storage, TaskList taskList, CommandList commandList,
                                DeletedTaskList deletedTaskList) throws DukeInvalidUndoException {
        
        Command undoCommand = commandList.removeLatestCommand();
        String[] undoCommandStrings = undoCommand.toString().split(" ");
        String commandWord = undoCommandStrings[0];
        
        switch (commandWord) {
        case "AddTaskCommand":
            int lastIndex = taskList.size() - 1;
            Task removedTask = taskList.removeTask(lastIndex);
            storage.writeDataToFile(taskList);
            ui.undoMessage("    Add Task " + removedTask.toString());
            break;
            
        case "DoneCommand":
            int doneIndex = Integer.parseInt(undoCommandStrings[1].replaceAll("[^0-9]", ""));
            assert doneIndex >= 0;
            taskList.getTask(doneIndex).setDone(false);
            storage.writeDataToFile(taskList);
            ui.undoMessage("    Done " + (doneIndex + 1));
            break;
            
        case "RemoveTaskCommand":
            int removeIndex = Integer.parseInt(undoCommandStrings[1].replaceAll("[^0-9]", ""));
            assert removeIndex >= 0;
            Task deletedTask = deletedTaskList.getLatestDelete();
            if (removeIndex >= taskList.size()) {
                taskList.addTask(deletedTask);
            } else {
                taskList.addTaskAtIndex(removeIndex, deletedTask);
            }
            storage.writeDataToFile(taskList);
            ui.undoMessage("    Delete Task " + deletedTask.toString());
            break;
            
        default:
            ui.undoMessage(commandWord);
        }
    }
}
