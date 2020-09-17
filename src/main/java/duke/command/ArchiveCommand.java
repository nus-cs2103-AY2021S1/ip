package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ArchiveCommand implements Command {
    private String filePath;

    public ArchiveCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        storage.saveToFile(tasks.toSaveFormat(), filePath);
        return Ui.show("You have successfully archive\ncurrent progress to the file(s) indicated");
    }
}
