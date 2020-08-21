package main.java.duke.command;

import java.io.IOException;
import java.time.LocalDate;
import main.java.duke.core.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

public class ListDateCommand extends Command{
    private LocalDate localDate;

    public ListDateCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        ui.showList(taskList.findTaskAt(localDate));
    }
}
