package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.exception.DukeTaskNotFoundException;

public class UndoCommand extends Command {

    private String[] commandDetails;

    public UndoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.getTasks().isEmpty() && taskNumber < tasks.getTasks().size()) {
            tasks.getTasks().get(taskNumber).undoTask();
            System.out.println(" Task has been undone: \n   "
                    + tasks.getTasks().get(taskNumber).toString());
        } else {
            throw new DukeTaskNotFoundException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
