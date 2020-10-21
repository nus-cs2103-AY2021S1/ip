package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * A command dealing with loading or unloading an existing Duke-list, or creating a new Duke-list.
 */
public class FileCommand extends Command {
    /** The type of the FileCommand. */
    private CommandType commandType;
    /** The type of the file associated with the FileCommand. */
    private String fileType;
    /** The filepath of the source (or destination) file. */
    private String filepath;

    /**
     * Constructs a new FileCommand object of the specified CommandType.
     *
     * @param commandType
     */
    public FileCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Constructs a new FileCommand object of the specified CommandType with the specified filepath.
     *
     * @param commandType The type of the FileCommand.
     * @param filepath The filepath where the concerned file is located.
     */
    public FileCommand(CommandType commandType, String fileType, String filepath) {
        this.commandType = commandType;
        this.fileType = fileType;
        this.filepath = filepath;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        switch (commandType) {
        case LOAD:
            storage.loadFile(filepath, fileType);
            ui.showLoadingSuccess(filepath);
            break;
        case CREATE:
            storage.makeNewFile(filepath, fileType);
            ui.showMakeFileSuccess(filepath);
            break;
        case UNLOAD:
            storage.reset();
            ui.showUnloaded();
            break;
        default:
            throw UserInputException.INVALID_COMMAND;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
