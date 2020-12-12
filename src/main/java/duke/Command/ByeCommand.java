package duke.Command;

import duke.AppUi;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class ByeCommand extends Command{

    private Storage storage;
    private TaskList list;
    private AppUi appUi;

    public ByeCommand(String input, Storage storage, TaskList list){
        super(input);
        this.storage = storage;
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute() throws IOException {
        storage.writeFile(list);
        return appUi.getByeMessage();
    }

}
