package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        int index;

        try {
            index = Integer.parseInt(command.substring(7));
            Task task = taskList.getStorage().get(index - 1);
            ui.print("     Noted, I have removed this task:");
            task.printTask();

            taskList.remove(task);
            ui.printListSize(taskList.listSize());
        } catch (Exception ex) {
            if (ex instanceof StringIndexOutOfBoundsException) {
                throw new SparklesException("     OOPS!! Task in the list to be deleted is not specified!");
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
