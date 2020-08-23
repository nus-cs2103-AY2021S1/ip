package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.exception.DukeTaskNotFoundException;
import main.java.duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a command which finds a task.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a new instance of a FindCommand.
     *
     * @param keyword Keyword to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the operation to find matching tasks.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @throws DukeTaskNotFoundException If no matching tasks are found.
     */
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

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
