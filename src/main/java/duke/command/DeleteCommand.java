package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    protected static final String DELETE_MESSAGE = "Noted. I've removed this task: ";

    private int itemIndex;

    private DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public static DeleteCommand parse(String command) {
        String[] details = command.split(" ");
        if (details.length == 1) {
            throw new DukeException("Please specify a task to delete!");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number input!");
        }
        return new DeleteCommand(taskNumber);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (itemIndex > taskList.size()) {
            throw new DukeException("Not a valid command!");
        }
        Task removedTask = taskList.remove(itemIndex);
        ui.outputMessage(createDeleteMessage(removedTask, taskList));
        storage.updateFile(taskList);
    }


    private String createDeleteMessage(Task taskRemoved, TaskList taskList) {
        return DELETE_MESSAGE + '\n'
                + "   " + taskRemoved + '\n'
                + taskList.createTaskNumberCountMessage();
    }


}
