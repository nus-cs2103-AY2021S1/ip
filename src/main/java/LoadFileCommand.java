import java.io.IOException;

public class LoadFileCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
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
