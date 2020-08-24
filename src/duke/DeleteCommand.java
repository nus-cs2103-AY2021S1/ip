package duke;

public class DeleteCommand extends Command {

    public DeleteCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processDelete(this.task, taskList, ui, storage);
        } catch (DeleteException d) {
            System.out.println(d.getMessage());
        }
    }

    public void processDelete(String theRest, TaskList taskList, Ui ui, Storage storage) throws DeleteException {
        try {
            Integer taskNum = Integer.parseInt(theRest);
            int index = taskNum - 1;
            taskList.deleteTask(index);
            storage.updateData(taskList.getTasks());

        } catch (DukeException d) {
            throw new DeleteException("Please enter a number. I cannot delete nothing :(");
        }
    }
}
