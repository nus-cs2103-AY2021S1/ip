package botbot.commands;

import botbot.Storage;
import botbot.tasks.Task;
import botbot.TaskList;
import botbot.Ui;

public class DeleteCommand extends Command {
    private int id;
    
    public DeleteCommand(int id) {
        this.id = id;
    }
    
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            tasks.delete(id);
            storage.save(tasks);
            int numOfTasks = tasks.size();
            ui.printStatus(String.format("    ok! I've removed this task:\n      %s\n    you now have "
                    + "%d task" + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
        } catch (IndexOutOfBoundsException e) {
            ui.printStatus("    oops! invalid task number!\n");
        }
    }
}
