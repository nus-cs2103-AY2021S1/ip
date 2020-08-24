package commands;

import storage.DataStorage;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command, String item) {
        super(command, item);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage ds) {
        int delNum = 0;

        try {
            delNum = Integer.parseInt(item);
        }
        catch (NumberFormatException ignored) {
        }

        if (delNum > 0 && delNum <= tasks.getTaskSize()) {
            System.out.println("Alright! I've deleted the task:\n  " + tasks.getTask(delNum-1));
            tasks.deleteTask(delNum-1);

            System.out.println("You now have " + tasks.getTaskSize() + " task(s) in your list!");
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
