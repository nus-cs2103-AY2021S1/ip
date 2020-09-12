import java.io.IOException;

public class LoadFileCommand extends Command {

    LoadFileCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    @Override
    public String execute() throws IOException {
        String filePath = storage.promptForFile();
        if (filePath.equals(Storage.noPath)) {
            return ui.printf("No new data file selected");
        } else {
            storage.loadFile(filePath);
            tasks.loadTaskFile(storage.load());
            return ui.printf("New data file has been loaded");
        }
    }
}
