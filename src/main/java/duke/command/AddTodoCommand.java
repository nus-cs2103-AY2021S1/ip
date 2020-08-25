package duke.command;

import duke.exception.*;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] taskInfo = this.retrieveTodoInfo(this.parsedCommand);
            Task toAdd = new Todo(taskInfo[0]);
            tasks.addTask(toAdd);

            String successReply = "  Success! This todo task has been added: \n\t" +
                    toAdd.toString() + "\n   You have " + tasks.getListSize() + " tasks in your list now.";
            ui.printReply(successReply);

            storage.saveFile(tasks);
        } catch (DukeException ex) {
            throw ex;
        }

    }

    public String[] retrieveTodoInfo(String[] parsedCommand) throws DukeException {
        String[] todoInfo = new String[1];
        if (parsedCommand.length == 0) {
            String err = "Your todo task description is empty. The task cannot be created.\n" +
                    "Type '/commands' to view the correct command for task creation! ";
            throw new InvalidTaskException(err);
        } else {
            todoInfo[0] = parsedCommand[1];
        }
        return todoInfo;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
