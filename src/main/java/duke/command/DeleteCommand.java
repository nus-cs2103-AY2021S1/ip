package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.exception.DukeTaskNotFoundException;
import main.java.duke.task.Task;

public class DeleteCommand extends Command {

    String[] commandDetails;

    public DeleteCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.getTasks().isEmpty() && taskNumber < tasks.getTasks().size()) {
            Task removedTask = tasks.getTasks().remove(taskNumber);
            System.out.println( String.format(" Noted. Target Scraped: \n   %s \n " +
                    "Now you have %d tasks in the list. ", removedTask.toString(), tasks.getTasks().size()));
        } else {
            throw new DukeTaskNotFoundException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
