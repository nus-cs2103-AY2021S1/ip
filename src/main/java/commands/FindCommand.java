package commands;

import duke.Storage;
import duke.Ui;
import tasks.Task;
import tasks.TaskList;

public class FindCommand extends Command {
    String text;

    public FindCommand(String text) {
        this.text = text;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Task task : tasks.getTasks()) {
            String name = task.getName();
            if (name.contains(text)) {
                output.append(counter == 1 ? "" : "\n").append(counter).append(".").append(task);
                counter++;
            }
        }
        ui.printFindTask(output.toString(), counter - 1);
    }
}
