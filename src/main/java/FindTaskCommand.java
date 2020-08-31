public class FindTaskCommand implements Command {
    private boolean isDone;
    private String searchKeyword;

    FindTaskCommand(String searchKeyword) {
        this.isDone = false;
        this.searchKeyword = searchKeyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: FindTaskCommand already executed");
        }

        TaskList matchingList = new TaskList();

        for (int i = 1; i <= taskList.getSize(); i++) {

            String nextTaskName = taskList.getTask(i).getName();
            if (nextTaskName.contains(this.searchKeyword)) {
                matchingList.addTask(taskList.getTask(i));
            }
        }

        ui.printMatchingTaskList(matchingList);

        this.isDone = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
