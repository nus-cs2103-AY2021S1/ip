package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FileCommand extends Command {
    private CommandType commandType;
    private String filepath;

    /**
     * Constructs a new FileCommand object of the specified CommandType with the specified filepath.
     *
     * @param commandType The type of the FileCommand.
     * @param filepath The filepath where the concerned file is located.
     */
    public FileCommand(CommandType commandType, String filepath) {
        this.commandType = commandType;
        this.filepath = filepath;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output;
        switch (this.commandType) {
        case LOAD:
            ArrayList<Task> fileTasks = storage.loadFromFilepath(filepath);
            assert(tasks.getNumTasks() == 0);
            tasks.loadTasks(fileTasks);
            output = ui.showLoadingSuccess(filepath);
            break;
        case CREATE:
            storage.makeFile(filepath);
            output = ui.showMakeFileSuccess(filepath);
            break;
        default:
            throw DukeException.INVALID_COMMAND_EXCEPTION;
        }
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
