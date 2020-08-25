public class DeleteCommand implements Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("The task index should be an index on the list!");
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveList(taskList);

        ui.giveResponse(" Noted. I've removed this task:\n       " +
                task +
                "\n\t Now you have " + taskList.size() +
                " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }
}
