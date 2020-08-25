package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.FindWrongFormatException;
import duke.task.Event;
import duke.task.Task;

public class FindCommand extends Command {

    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FindWrongFormatException {
        try {
            String keyWords = fullCommand.substring(5);
            TaskList foundTasks = new TaskList();
            for (Task task : tasks.getTaskList()) {
                if (task.getDescription().contains(keyWords)) {
                    foundTasks.addTask(task);
                } else if (task instanceof Event && ((Event) task).getAt().contains(keyWords)) {
                    foundTasks.addTask(task);
                }
            }
            ui.showFoundTaskList(foundTasks, keyWords);
        } catch (IndexOutOfBoundsException e) {
            throw new FindWrongFormatException();
        }
    }
}
