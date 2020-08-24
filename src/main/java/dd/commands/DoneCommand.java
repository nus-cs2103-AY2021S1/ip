package commands;

import storage.DataStorage;
import tasks.TaskList;
import ui.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command, String item) {
        super(command, item);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage ds) {
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(item);
        }
        catch (NumberFormatException ignored) {
        }

        if (taskNum > 0 && taskNum <= tasks.getTaskSize()) {
            tasks.getTaskList().get(taskNum - 1).markAsDone();
            System.out.println("Wow!! Good job!!\n  " + tasks.getTaskList().get(taskNum - 1));
        }
        else {
            System.out.println("hmm.. I don't think thats a valid task, try again?");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}