public class DoneCommand extends Command {
    public String commandWord = "done";
    public TaskList taskList;
    public String filePath;
    public String secondWord;

    public DoneCommand(TaskList taskList, String filePath, String secondWord) {
        super("done");
        this.taskList = taskList;
        this.filePath = filePath;
        this.secondWord = secondWord;
    }

    @Override
    public String execute() {
        try {
            int task = Integer.parseInt(secondWord); // get the task number
            if (task > taskList.noOfTasks) {
                return Ui.uncreatedTask(); // task has not been created
            } else {
                assert (taskList.list.get(task - 1) != null) : "Incorrect index.";
                Task cur = taskList.list.get(task - 1);
                if (cur.getStatus()) {
                    return Ui.duplicatedMarkDone();
                }
                cur.markAsDone();
                Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
                return Ui.markDoneSuccessful(cur);
            }
        } catch (NumberFormatException e) {
            return Ui.incorrectDoneFormat();
        }
    }
}
