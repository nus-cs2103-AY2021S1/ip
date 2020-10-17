import java.io.IOException;

public class LoadFileCommand extends Command {

    /**
     * Constructor for LoadFileCommand
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    LoadFileCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    /**
     * Executes the LoadFileCommand
     * @return Returns String confirmation of file loaded
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        String filePath = Storage.promptForFile();
        if (filePath.equals(Storage.NOPATH)) {
            return ui.printf("No new data file selected");
        } else {
            storage.loadFile(filePath);
            tasks.loadTaskFile(storage.load());
            return ui.printf("New data file has been loaded");
        }
    }
}
