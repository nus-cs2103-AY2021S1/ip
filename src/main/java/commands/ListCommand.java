package main.java.commands;

import main.java.tasklist.*;
import main.java.ui.*;
import main.java.storage.*;
import java.time.LocalDate;

public class ListCommand extends Command {
    private final LocalDate date;

    public ListCommand(LocalDate date){
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (date == null) {
            ui.showTask(tasks);
        } else {
            ui.showTask(tasks, date);
        }
    }

}
