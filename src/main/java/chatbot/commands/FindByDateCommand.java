package chatbot.commands;

import chatbot.data.Task;
import chatbot.data.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.function.Predicate;

public class FindByDateCommand extends Command {

    Predicate<Task> pred;

    public FindByDateCommand(LocalDate date) {
        this.pred = task -> (task.getDate() != null && task.getDate().equals(date));
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.retrieveTasksByPred(pred);
        String response = ui.list(tasks);

        return response;
    }
}
