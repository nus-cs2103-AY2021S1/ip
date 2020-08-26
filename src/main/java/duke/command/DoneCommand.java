package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;

public class DoneCommand extends Command {

    private final String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        String[] input = fullCommand.split(" ");
        Task task;
        if (input.length == 2) {
            task = taskList.retrieveTask(Integer.parseInt(input[1]) - 1);
            task.markAsDone();
        } else {
            throw new DukeException("Cannot mark item done!");
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("%s\n", task);
        ui.showLine();

        storage.write(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
