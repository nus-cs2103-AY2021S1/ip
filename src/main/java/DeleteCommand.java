public class DeleteCommand implements Command {

    protected static final String DELETE_MESSAGE = "Noted. I've removed this task: ";

    private int itemIndex;

    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
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
