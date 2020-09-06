package juke.command;

import juke.Storage;
import juke.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        try {
            storage.saveTasksToFile();
            return "That's it? That's a shame. Well, see you later then.";
        } catch (IOException exception) {
            return exception.getMessage();
        }
    }
}
