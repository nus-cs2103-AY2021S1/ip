/**
 * The DoneCommand inherits from Command and is used whenever the markAsDone() method
 * from Task is called. This marks done the specified task in the TaskList
 * and saves it.
 */
class DoneCommand extends Command {

    private int taskNum;

    /**
     * The constructor for a DoneCommand takes in a task number specified by the user
     * to mark done the specific task in the TaskList.
     *
     * @param taskNum The task number of a particular task.
     */
    DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * The execute method in DoneCommand serves to update the TaskList with the updated status
     * of the specified task and saves it.
     *
     * @param tasks This is the saved TaskList the saved file.
     * @return A boolean to indicate whether the program should be exited.
     */
    @Override
    boolean execute(TaskList tasks) {
        Ui.printTask(tasks.markDone(taskNum), Action.DONE, tasks.getSize() - 1);
        return false;
    }
}