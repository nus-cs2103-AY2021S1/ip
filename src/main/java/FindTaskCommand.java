public class FindTaskCommand implements Command {
    private boolean isDone;

    /** The search keyword used to find tasks */
    private String searchKeyword;

    FindTaskCommand(String searchKeyword) {
        this.isDone = false;


        this.searchKeyword = searchKeyword;
    }

    /**
     * Finds the list of tasks whose names contain the searchKeyword and prints the list to the console.
     *
     * @param taskList the running TaskList in use
     * @param ui the Ui object that is used to print the action to the console
     * @param storage the Storage object in use
     * @throws CartonaException if the command has already been executed
     */
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
