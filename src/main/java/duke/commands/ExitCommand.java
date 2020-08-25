package duke.commands;

import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.ResourceHandler;
import duke.utils.Ui;

public class ExitCommand extends Command{
    @Override
    public void executeCommand(TaskManager taskManager, Ui formatter, Storage storage) {
        formatter.print(ResourceHandler.getMessage("commandline.farewellMessage"));
        try {
            storage.save(taskManager);
        } catch (StorageOperationException e){
            formatter.print(Colour.Red(e.getMessage()));
        }
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
