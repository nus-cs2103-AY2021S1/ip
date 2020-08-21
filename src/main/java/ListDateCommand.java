package main.java;

import java.io.IOException;
import java.time.LocalDate;

public class ListDateCommand extends Command{
    private LocalDate localDate;

    ListDateCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        ui.showList(taskList.findTaskAt(localDate));
    }
}
