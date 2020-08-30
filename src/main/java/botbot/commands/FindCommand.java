package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;
import botbot.tasks.Task;

public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList matches = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        ui.printStatus("    here are the matches in your list:\n" + matches);
    }
}
