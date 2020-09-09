package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ArchiveCommand extends Command {

    private String[] nextCommandArr;
    public ArchiveCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.nextCommandArr.length == 2) {
            try {
                String archivePath = nextCommandArr[1];
                storage.archive(taskList, archivePath);
                taskList.newList();
                return ui.archiveText();
            } catch (Exception e) {
                return new DukeException("Please provide a valid archive path~").toString();
            }
        } else {
            return new DukeException("Please provide an archive path~").toString();
        }
    }

    @Override
    public boolean continueRunning() {
        return true;
    }
}
