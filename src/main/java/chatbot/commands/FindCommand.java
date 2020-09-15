package chatbot.commands;

import java.util.ArrayList;
import java.util.function.Predicate;

import chatbot.data.Task;
import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class FindCommand extends Command {

    private final Predicate<Task> pred;

    public FindCommand(Predicate<Task> pred) {
        this.pred = pred;
    }

    public Predicate<Task> getPred() {
        return pred;
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
