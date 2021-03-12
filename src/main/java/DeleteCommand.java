public class DeleteCommand extends Command {
    public String commandWord = "delete";
    public TaskList taskList;
    public String filePath;
    public String secondWord;

    public DeleteCommand(TaskList taskList, String filePath, String secondWord) {
        super("list");
        this.taskList = taskList;
        this.filePath = filePath;
        this.secondWord = secondWord;
    }

    @Override
    public String execute() {
        int num = Integer.valueOf(secondWord);
        if (num > taskList.noOfTasks) {
            return Ui.uncreatedTask();
        } else {
            assert (taskList.list.get(num - 1) != null) : "Incorrect index.";
            Task removed = taskList.list.get(num - 1);
            taskList.deleteTask(removed);
            Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath); // update storage
            return Ui.deleteSuccessful(removed, taskList);
        }
    }
}
