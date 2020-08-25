package main.java.commands;

import main.java.storage.Storage;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

import java.time.LocalDate;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTask(tasks, keyword);
    }

}
