package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.TodoWrongFormatException;
import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

public class AddTodoCommand extends AddCommand {

    private String fullCommand;

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TodoWrongFormatException {
        try {
            Task newTask = new ToDo(fullCommand.substring(5).trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException e)
        { // duke.command.Command is in a wrong format
            throw new TodoWrongFormatException();
        }
    }
}
