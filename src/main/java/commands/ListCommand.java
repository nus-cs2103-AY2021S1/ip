package java.commands;

import java.tasklist.*;
import java.ui.*;
import java.storage.*;
import java.time.LocalDate;

public class ListCommand extends Command {
    private final LocalDate date;

    public ListCommand(LocalDate date){
        this.date = date;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        if (date == null) {
            output = ui.showTask(tasks);

        } else {
            output = ui.showTask(tasks, date);
        }

        return output;
    }

}
