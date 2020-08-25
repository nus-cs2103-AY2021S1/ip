package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    public ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            String emptyListMessage = "Your list is empty. Add a new task!";
            ui.printReply(emptyListMessage);
        } else {
            String message = "Here is a list of all your tasks:";
            ui.printReply(message);
            for (int i = 0; i < tasks.getListSize(); i++) {
                int index = i + 1;
                String task = "\t" + String.format("%d. %s", index, tasks.getTask(i));
                ui.printReply(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
