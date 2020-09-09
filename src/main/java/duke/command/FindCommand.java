package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exception.EmptyListException;
import duke.task.Task;

public class FindCommand extends Command {

    public FindCommand() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.printFindPrompt();
        String keyword = ui.readCommand();
        ui.printFoundTasksHeader();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                ui.printTask(taskList, i);
            } else {
                continue;
            }
        }
    }
}