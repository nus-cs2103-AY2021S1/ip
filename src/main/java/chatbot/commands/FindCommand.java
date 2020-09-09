package chatbot.commands;

import chatbot.data.Task;
import chatbot.data.TaskList;

import chatbot.storage.Storage;

import chatbot.ui.Ui;

import java.util.ArrayList;
import java.util.function.Predicate;

public class FindCommand extends Command {

    Predicate<Task> pred;

    public FindCommand(Predicate<Task> pred) {
        this.pred = pred;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.retrieveTasksByPred(pred);
        return ui.list(tasks);
    }
}
