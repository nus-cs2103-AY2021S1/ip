package duke.commands;

import duke.DeletedTaskList;
import duke.CommandList;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

import duke.exceptions.DukeInvalidUndoException;
import duke.tasks.Task;

public class UndoCommand extends Command {
    
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
            if (removeIndex >= taskList.size() - 1) {
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
