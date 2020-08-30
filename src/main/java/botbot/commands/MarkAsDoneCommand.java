package botbot.commands;

import botbot.Storage;
import botbot.tasks.Task;
import botbot.TaskList;
import botbot.Ui;

public class MarkAsDoneCommand extends Command {
    private int id;
    
    public MarkAsDoneCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            task.markAsDone();
            storage.save(tasks);
            ui.printStatus("    nice! I've marked this task as done:\n      " + task + "\n");
        } catch (IndexOutOfBoundsException e) {
            ui.printStatus("    oops! invalid task number!\n");
        }
    }
}
