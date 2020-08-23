package main.java;

public class DeleteCommand extends Command {

    String[] commandDetails;

    public DeleteCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.getTasks().isEmpty() && taskNumber < tasks.getTasks().size()) {
            Task removedTask = tasks.getTasks().remove(taskNumber);
            System.out.println( String.format(" Noted. Target Scraped: \n   %s \n " +
                    "Now you have %d tasks in the list. ", removedTask.toString(), tasks.getTasks().size()));
        } else {
            throw new DukeException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
