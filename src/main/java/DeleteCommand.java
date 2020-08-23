package main.java;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    String[] commandDetails;

    public DeleteCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException{
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.isEmpty() && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            System.out.println( String.format("~ \n Noted. Target Scraped: \n   %s \n " +
                    "Now you have %d tasks in the list. \n~", removedTask.toString(), tasks.size()));
        } else {
            throw new DukeException("~\n ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN \n~");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
