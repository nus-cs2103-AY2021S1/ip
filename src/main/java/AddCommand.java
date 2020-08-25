public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        super();
        this.task = task;
    }

    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(task);
        Ui.showMessage(String.format("Okay! I have added the task:\n%s\n", task));
        Ui.showMessage(String.format(
                "Currently you have %d tasks in your list, don't forget to do them!\n",
                taskList.getSize()));
        storage.writeToFile(taskList);
    }
}
