package duke;

/**
 * Adds a task to the tasklist command.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * AddCommand constructor.
     *
     * @param task task to be added to the tasklist.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        assert task != null;
        taskList.addTask(task);
        ui.addMessage(String.format("Okay! I have added the task:\n%s\n", task));
        ui.addMessage(String.format("Currently you have %d tasks in your list, don't forget to do them!\n",
                taskList.getSize()));
        storage.writeToFile(taskList);
    }
}
