package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.exception.DukeTaskNotFoundException;
import main.java.duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                taskArrayList.add(task);
            }
        }
        if (!taskArrayList.isEmpty()) {
            ui.showMatchingTask(taskArrayList);
        } else {
            throw new DukeTaskNotFoundException(" NO MATCHING TASK FOUND. \n PLEASE TRY AGAIN. ");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
