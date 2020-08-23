package main.java;

import java.util.ArrayList;

public class DoneCommand extends Command {

    String[] commandDetails;

    public DoneCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.isEmpty() && taskNumber < tasks.size()) {
            tasks.get(taskNumber).doneTask();
            System.out.println("~ \n Nice! Target Eliminated: \n   "
                    + tasks.get(taskNumber).toString() + "\n~");
        } else {
            throw new DukeException("~\n ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN \n~");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
