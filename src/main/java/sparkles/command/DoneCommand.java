package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        int index;

        try {
            index = Integer.parseInt(command.substring(5));
            Task task = taskList.getStorage().get(index - 1);
            if (task.getStatusIcon().equals("O")) {
                ui.print("     Task is already marked as done!");
            } else {
                task.markAsDone();
                ui.print("     Nice! I have marked this task as done :-)");
            }
        } catch (Exception ex) {
            if (ex instanceof StringIndexOutOfBoundsException) {
                throw new SparklesException("     OOPS!! Task in the list to be marked as done is not specified!");
            } else {
                if(taskList.getStorage().isEmpty()) {
                    throw new SparklesException( "     OOPS!! Task list is empty!");

                } else {
                    throw new SparklesException("     OOPS!! Task does not exist!");
                }
            }
        } finally {
            storage.updateFile(taskList.getStorage());
        }
    }
}
